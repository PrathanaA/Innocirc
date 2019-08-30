package com.proficiency.innocirc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public List<Brand_bean> brandList = new ArrayList<>();
    BrandAdapter brandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Identify RecyclerView widget
        recyclerView=findViewById(R.id.brand_recyclerview);

        //Setting GridLayoutManager to RecyclerView
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Creating Instance of Custom Adapter and set to RecyclerView
        brandAdapter=new BrandAdapter(this,brandList);
        recyclerView.setAdapter(brandAdapter);

        //Getting all Brand details
        gettingBrandList();


    }

    private void gettingBrandList(){
        JSONObject userRequestjsonObject = new JSONObject();

        try {
            userRequestjsonObject.put("LookingForDetailsId", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Voleypost.getting_brand_(this,Urls.GetBrandList,userRequestjsonObject, new VoleyJsonObjectCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) {

                        JSONArray cropsListArray=null;
                        try {
                            cropsListArray=result.getJSONArray("BrandList");
                            for (int i=0;i<cropsListArray.length();i++){

                                JSONObject jsonObject1=cropsListArray.getJSONObject(i);
                                String brand_name=jsonObject1.getString("BrandName");
                                String BrandIcon=jsonObject1.getString("BrandIcon");
                                Brand_bean brand_bean = new Brand_bean(BrandIcon, brand_name);
                                brandList.add(brand_bean);

                            }
                            brandAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }

        );

    }
}
