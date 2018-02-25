package com.blcheung.cityconstruction.coolweather.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blcheung.cityconstruction.coolweather.R;
import com.blcheung.cityconstruction.coolweather.db.City;
import com.blcheung.cityconstruction.coolweather.db.County;
import com.blcheung.cityconstruction.coolweather.db.Province;
import com.blcheung.cityconstruction.coolweather.util.HttpUtil;
import com.blcheung.cityconstruction.coolweather.util.Utility;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by BLCheung.
 * Date:2018年2月5日,0005 1:47
 */

public class ChooseAreaFragment extends Fragment {
    // 省级
    public static final int LEVEL_PROVINCE = 0;
    // 市级
    public static final int LEVEL_CITY = 1;
    // 县/区级
    public static final int LEVEL_COUNTY = 2;
    private TextView tvTitle;
    private Button btnBack;
    private ProgressDialog progressDialog;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();
    /**
     * 省列表
     */
    private List<Province> provinceList;
    /**
     * 市列表
     */
    private List<City> cityList;
    /**
     * 县/区列表
     */
    private List<County> countyList;
    /**
     * 选中的省
     */
    private Province selectedProvince;
    /**
     * 选中的市
     */
    private City selectedCity;
    /**
     * 选中的县/区
     */
    private County selectedCounty;
    /**
     * 当前选中的级别
     */
    private int currentLevel;
    private String adress = "http://guolin.tech/api/china";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);
        tvTitle = view.findViewById(R.id.tv_title);
        btnBack = view.findViewById(R.id.btn_back);
        listView = view.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(position);
                    queryCities();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_COUNTY) {
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    queryProvinces();
                }
            }
        });
        queryProvinces();
    }

    /**
     * 查询全国省份,优先从数据库查询,如果没有则从服务器从获取数据
     */
    private void queryProvinces() {
        tvTitle.setText("中国");
        // 省级列表为顶级列表不必显示返回按钮
        btnBack.setVisibility(View.GONE);
        // 优先从数据库中查询数据
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            queryFromServer(adress, LEVEL_PROVINCE);
        }
    }

    /**
     * 查询选中的省内所有城市,优先从数据库查询,如果没有则从服务器从获取数据
     */
    private void queryCities() {
        tvTitle.setText(selectedProvince.getProvinceName());
        btnBack.setVisibility(View.VISIBLE);
        // 查询被选中的省内的所有城市
        cityList = DataSupport.where("provinceid = ?",
                String.valueOf(selectedProvince.getId())).find(City.class);
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            int provinceCode = selectedProvince.getProvinceCode();
            queryFromServer(adress + provinceCode, LEVEL_CITY);
        }
    }

    /**
     * 查询被选中的市内所有县/区,优先从数据库查询,如果没有则从服务器从获取数据
     */
    private void queryCounties() {
        tvTitle.setText(selectedCity.getCityName());
        btnBack.setVisibility(View.VISIBLE);
        // 查询被选中的城市内所有区/县
        countyList = DataSupport.where("cityid = ?",
                String.valueOf(selectedCity.getCityCode())).find(County.class);
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_COUNTY;
        } else {
            int provinceCode = selectedProvince.getProvinceCode();
            int cityCode = selectedCity.getCityCode();
            queryFromServer(adress + provinceCode + "/" + cityCode, LEVEL_COUNTY);
        }
    }

    /**
     * 根据传入的地址和各省,市,县/区级别从服务器获取省市县数据
     * @param adress 服务器地址
     * @param level 省,市,县/区级别
     */
    private void queryFromServer(String adress, final int level) {
        // 显示加载对话框
        showProgressDialog();
        HttpUtil.sendOkHttpRequest(adress, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                boolean result = false;
                if (level == LEVEL_PROVINCE) {
                    result = Utility.handlerProvinceResponse(responseData);
                } else if (level == LEVEL_CITY) {
                    result = Utility.handlerCityResponse(responseData, selectedProvince.getId());
                } else if (level == LEVEL_COUNTY) {
                    result = Utility.handlerCountyResponse(responseData, selectedCity.getId());
                }
                if (result) {
                    // 切换主线程更新UI
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            if (level == LEVEL_PROVINCE) {
                                queryProvinces();
                            } else if (level == LEVEL_CITY) {
                                queryCities();
                            } else if (level == LEVEL_COUNTY) {
                                queryCounties();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(getContext(), "加载失败!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 显示对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
