package id.web.utem.mymoney;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private final String apiLink = "http://mymoney-api.utem.web.id/api/v1/";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String user_id = "0";

    TextView tvDailyIncomeAmount;
    TextView tvDailyOutcomeAmount;
    TextView tvMonthlyIncomeAmount;
    TextView tvMonthlyOutcomeAmount;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);;
        tvDailyIncomeAmount = rootView.findViewById(R.id.tvDailyIncomeAmount);
        tvDailyOutcomeAmount = rootView.findViewById(R.id.tvDailyOutcomeAmount);
        tvMonthlyIncomeAmount = rootView.findViewById(R.id.tvMonthlyIncomeAmount);
        tvMonthlyOutcomeAmount = rootView.findViewById(R.id.tvMonthlyOutcomeAmount);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        pref = getActivity().getApplicationContext().getSharedPreferences("MyMoney_Pref", 0);
        editor = pref.edit();

        user_id = pref.getString("user_id", "0");
        loadData();
        super.onViewCreated(view, savedInstanceState);
    }

    private void loadData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiLink +"dashboard.php?method=get&user_id="+user_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Log.d("A", jsonObject.toString());
                    tvDailyIncomeAmount.setText(jsonObject.getString("today_income"));
                    tvDailyOutcomeAmount.setText(jsonObject.getString("today_outcome"));
                    tvMonthlyIncomeAmount.setText(jsonObject.getString("monthly_income"));
                    tvMonthlyOutcomeAmount.setText(jsonObject.getString("monthly_outcome"));

                    Toast.makeText(getActivity().getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "There is an error!", Toast.LENGTH_SHORT).show();
                    Log.d("A", "Error!", e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Unable to get data for dashboard", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }

}
