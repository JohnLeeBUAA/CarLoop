package com.buf.carloop;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CarpoolList extends Footer {

    private String type;
    private LinearLayout buttonlist;
    private TextView tip;
    private ListView listview;
    private List<Carpool> list;
    private Button btn_demanded;
    private Button btn_interested;
    private Button btn_confirmed;
    private LinearLayout sortlist;
    private Button btn_sort;
    private int sortmethod;
    private int tempsortmethod;

    private double search_depart_lat;
    private double search_depart_lng;
    private double search_desti_lat;
    private double search_desti_lng;

    private final double sort_weight_walking_distance = 0.4D;
    private final double sort_weight_departure_time = 0.2D;
    private final double sort_weight_driver_rating = 0.2D;
    private final double sort_weight_carpool_price = 0.2D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup vg = (ViewGroup) findViewById(R.id.content);
        ViewGroup.inflate(this, R.layout.activity_carpool_list, vg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonlist = (LinearLayout) findViewById(R.id.buttonlist_carpool_list);
        sortlist = (LinearLayout) findViewById(R.id.sortlist_carpool_list);
        sortlist.setVisibility(View.GONE);
        btn_sort = (Button) findViewById(R.id.btn_sort_carpoollist);
        listview = (ListView) findViewById(R.id.list_carpoollist);
        tip = (TextView) findViewById(R.id.tip_carpoollist);
        btn_demanded = (Button) findViewById(R.id.btn_demanded_carpoollist);
        btn_interested = (Button) findViewById(R.id.btn_interested_carpoollist);
        btn_confirmed = (Button) findViewById(R.id.btn_confirmed_carpoollist);

        search_depart_lat = getIntent().getDoubleExtra("depart_lat_val", 0D);
        search_depart_lng = getIntent().getDoubleExtra("depart_lng_val", 0D);
        search_desti_lat = getIntent().getDoubleExtra("desti_lat_val", 0D);
        search_desti_lng = getIntent().getDoubleExtra("desti_lng_val", 0D);
        
        type = getIntent().getStringExtra("type");
        if(type.equals("Search")) {
            this.setTitle("Search Result");
            buttonlist.setVisibility(View.GONE);
            sortlist.setVisibility(View.VISIBLE);
            list = Carpool.getSearchList(
                    GlobalVariables.user_name,
                    getIntent().getDoubleExtra("depart_lat_val", 0D),
                    getIntent().getDoubleExtra("depart_lng_val", 0D),
                    getIntent().getDoubleExtra("desti_lat_val", 0D),
                    getIntent().getDoubleExtra("desti_lng_val", 0D),
                    getIntent().getStringExtra("date"),
                    getIntent().getStringExtra("time"),
                    getIntent().getStringExtra("date_range"),
                    getIntent().getStringExtra("time_range"));
            btn_sort.setText("\tAdvanced Sort\t");
            sortmethod = 1;
            sortAdvacedSort();
            longDistanceFilter();
            if(list == null) {
                tip.setText("Network error");
                Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
            }
            else if(list.size() == 0) {
                tip.setText("No Matched Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
                populateListView();
                registerClickCallback();
            }
        }
        else if(type.equals("SearchDemanded")) {
            this.setTitle("Search Demanded Result");
            buttonlist.setVisibility(View.GONE);
            sortlist.setVisibility(View.GONE);
            list = Carpool.getSearchDemandedList(
                    GlobalVariables.user_name,
                    getIntent().getDoubleExtra("depart_lat_val", 0D),
                    getIntent().getDoubleExtra("depart_lng_val", 0D),
                    getIntent().getDoubleExtra("desti_lat_val", 0D),
                    getIntent().getDoubleExtra("desti_lng_val", 0D),
                    getIntent().getStringExtra("date"),
                    getIntent().getStringExtra("time"),
                    getIntent().getStringExtra("date_range"),
                    getIntent().getStringExtra("time_range"));
            longDistanceFilter();
            if(list == null) {
                tip.setText("Network error");
                Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
            }
            else if(list.size() == 0) {
                tip.setText("No Matched Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
                populateListView();
                registerClickCallback();
            }
        }
        else if(type.equals("Created")) {
            this.setTitle("Created List");
            buttonlist.setVisibility(View.GONE);

            list = Carpool.getCreatedList(GlobalVariables.user_name);
            if(list == null) {
                tip.setText("Network error");
                Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
            }
            else if(list.size() == 0) {
                tip.setText("No Created Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
                populateListView();
                registerClickCallback();
            }
        }
        else if(type.equals("Demanded")) {
            this.setTitle("Demanded List");
            btn_demanded.setBackgroundResource(R.drawable.select_border);
            list = Carpool.getDemandedList(GlobalVariables.user_name);
            if(list == null) {
                tip.setText("Network error");
                Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
            }
            else if(list.size() == 0) {
                tip.setText("No Demanded Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
                populateListView();
                registerClickCallback();
            }
        }
        else if(type.equals("Interested")) {
            this.setTitle("Interested List");
            btn_interested.setBackgroundResource(R.drawable.select_border);
            list = Carpool.getInterestedList(GlobalVariables.user_name);
            if(list == null) {
                tip.setText("Network error");
                Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
            }
            else if(list.size() == 0) {
                tip.setText("No Interested Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
                populateListView();
                registerClickCallback();
            }
        }
        else if(type.equals("Confirmed")) {
            this.setTitle("Confirmed List");
            btn_confirmed.setBackgroundResource(R.drawable.select_border);
            list = Carpool.getConfirmedList(GlobalVariables.user_name);
            if(list == null) {
                tip.setText("Network error");
                Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
            }
            else if(list.size() == 0) {
                tip.setText("No Confirmed Carpool");
            }
            else {
                tip.setVisibility(View.GONE);
                populateListView();
                registerClickCallback();
            }
        }
    }

    private void populateListView() {
        ArrayAdapter<Carpool> adapter = new MyListAdapter();
        listview.setAdapter(adapter);
    }

    private void registerClickCallback() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {
                if (type.equals("SearchDemanded")) {
                    Intent intent = new Intent(CarpoolList.this, CarpoolNew.class);
                    intent.putExtra("carpool", list.get(position));
                    intent.putExtra("type", "CreateOnDemand");
                    startActivity(intent);
                    list.remove(position);
                    populateListView();
                    registerClickCallback();
                }
                else {
                    Intent intent = new Intent(CarpoolList.this, CarpoolSingle.class);
                    intent.putExtra("carpool", list.get(position));
                    if (type.equals("Created") && list.get(position).getStatus() == 1) {
                    /*
                    carpool trip for drivers
                     */
                        intent.putExtra("type", "Trip");
                    } else {
                    /*
                    Created, Interested, Confirmed, Search, Demanded
                     */
                        intent.putExtra("type", type);
                    }
                    startActivity(intent);
                    if (type.equals("Search")) {
                        list.remove(position);
                        populateListView();
                        registerClickCallback();
                    }
                }
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<Carpool> {
        public MyListAdapter() {
            super(CarpoolList.this, R.layout.item_carpool, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_carpool, parent, false);
            }

            Carpool carpool = list.get(position);

            ImageView driveravatar = (ImageView)itemView.findViewById(R.id.item_driveravatar);
            byte[] avatarimage = list.get(position).getDriveravatar();
            if (avatarimage != null) {
                Bitmap bm = BitmapFactory.decodeByteArray(avatarimage, 0, avatarimage.length);
                driveravatar.setImageBitmap(bm);
            }
            else {
                driveravatar.setImageResource(R.drawable.default_avatar);
            }

            TextView drivername = (TextView) itemView.findViewById(R.id.item_drivername);
            drivername.setText(carpool.getDrivername());

            RatingBar rb = (RatingBar) itemView.findViewById(R.id.item_ratingbar_list);
            if(type.equals("Demanded") || type.equals("SearchDemanded")) {
                rb.setVisibility(View.GONE);
            }
            else {
                rb.setNumStars(5);
                rb.setRating((float) (carpool.getDriverrate()));
            }

            TextView depart_loc = (TextView) itemView.findViewById(R.id.item_depart_loc);
            depart_loc.setText(carpool.getDepart_loc());

            TextView desti_loc = (TextView) itemView.findViewById(R.id.item_desti_loc);
            desti_loc.setText(carpool.getDesti_loc());

            TextView date = (TextView) itemView.findViewById(R.id.item_date);
            if(carpool.getDate_range().equals(carpool.getDate())) {
                date.setText(carpool.getDate());
            }
            else {
                date.setText(carpool.getDate() + " - " + carpool.getDate_range());
            }

            TextView time = (TextView) itemView.findViewById(R.id.item_time);
            if(carpool.getTime_range().equals(carpool.getTime())) {
                time.setText(carpool.getTime());
            }
            else {
                time.setText(carpool.getTime() + " - " + carpool.getTime_range());
            }

            TextView price = (TextView) itemView.findViewById(R.id.item_price);
            price.setText("$" + Integer.toString(carpool.getPrice()));

            TextView maxpassenger = (TextView) itemView.findViewById(R.id.item_maxpassenger);
            maxpassenger.setText(Integer.toString(carpool.getMaxpassenger()));

            TextView passengerconfirmed = (TextView) itemView.findViewById(R.id.item_passengerconfirmed);
            passengerconfirmed.setText(Integer.toString(carpool.getPassengerconfirmed()));

            return itemView;
        }
    }

    public void jumpDemandedList(View view) {
        Intent intent = new Intent(this, CarpoolList.class);
        intent.putExtra("type", "Demanded");
        startActivity(intent);
        finish();
    }

    public void jumpInterestedList(View view) {
        Intent intent = new Intent(this, CarpoolList.class);
        intent.putExtra("type", "Interested");
        startActivity(intent);
        finish();
    }

    public void jumpConfirmedList(View view) {
        Intent intent = new Intent(this, CarpoolList.class);
        intent.putExtra("type", "Confirmed");
        startActivity(intent);
        finish();
    }

    public void sortSearchList(View view) {
        final CharSequence[] choice = {"Advanced Sort", "Walking Distance", "Departure Time", "Driver Rating", "Carpool Price"};

        AlertDialog.Builder alert = new AlertDialog.Builder(CarpoolList.this);
        alert.setTitle("Sort List By");
        alert.setSingleChoiceItems(choice, sortmethod - 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (choice[which] == "Advanced Sort") {
                    tempsortmethod = 1;
                } else if (choice[which] == "Walking Distance") {
                    tempsortmethod = 2;
                } else if (choice[which] == "Departure Time") {
                    tempsortmethod = 3;
                } else if (choice[which] == "Driver Rating") {
                    tempsortmethod = 4;
                } else if (choice[which] == "Carpool Price") {
                    tempsortmethod = 5;
                }
            }
        });
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sortmethod = tempsortmethod;
                if (sortmethod == 1) {
                    btn_sort.setText("\tAdvanced Sort\t");
                    sortAdvacedSort();
                } else if (sortmethod == 2) {
                    btn_sort.setText("\tWalking Distance\t");
                    sortWalkingDistance();
                } else if (sortmethod == 3) {
                    btn_sort.setText("\tDeparture Time\t");
                    sortDepartureTime();
                } else if (sortmethod == 4) {
                    btn_sort.setText("\tDriver Rating\t");
                    sortDriverRating();
                } else if (sortmethod == 5) {
                    btn_sort.setText("\tCarpool Price\t");
                    sortCarpoolPrice();
                }
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.show();
    }

    private double getWalkDistance(double p1_lat, double p1_lng, double p2_lat, double p2_lng) {
        return Math.pow(Math.abs(p1_lat - p2_lat), 2) + Math.pow(Math.abs(p1_lng - p2_lng), 2);
    }

    private int compareWalkingDistance(Carpool lhs, Carpool rhs) {
        double ldist = getWalkDistance(search_depart_lat, search_depart_lng, lhs.getDepart_lat(), lhs.getDepart_lng()) +
                getWalkDistance(search_desti_lat, search_desti_lng, lhs.getDesti_lat(), lhs.getDesti_lng());
        double rdist = getWalkDistance(search_depart_lat, search_depart_lng, rhs.getDepart_lat(), rhs.getDepart_lng()) +
                getWalkDistance(search_desti_lat, search_desti_lng, rhs.getDesti_lat(), rhs.getDesti_lng());
        if(ldist > rdist) return 1;
        else if(ldist < rdist) return -1;
        else return 0;
    }

    private int compareDepartureTime(Carpool lhs, Carpool rhs) {
        int compare = lhs.getDate().compareTo(rhs.getDate());
        if(compare == 0) {
            compare = lhs.getTime().compareTo(rhs.getTime());
        }
        if(compare > 0) return 1;
        else if(compare < 0) return -1;
        else return 0;
    }

    private int compareDriverRating(Carpool lhs, Carpool rhs) {
        if(lhs.getDriverrate() > rhs.getDriverrate()) return 1;
        else if(lhs.getDriverrate() < rhs.getDriverrate()) return -1;
        else return 0;
    }

    private int compareCarpoolPrice(Carpool lhs, Carpool rhs) {
        if(lhs.getPrice() > rhs.getPrice()) return 1;
        else if(lhs.getPrice() < rhs.getPrice()) return -1;
        else return 0;
    }

    private int compareAdvancedSort(Carpool lhs, Carpool rhs) {
        double compare = compareWalkingDistance(lhs, rhs) * sort_weight_walking_distance +
                compareDepartureTime(lhs, rhs) * sort_weight_departure_time +
                compareDriverRating(lhs, rhs) * sort_weight_driver_rating +
                compareCarpoolPrice(lhs, rhs) * sort_weight_carpool_price;
        if(compare > 0) return 1;
        else if(compare < 0) return -1;
        else return 0;
    }

    private void sortAdvacedSort() {
        if(list != null && list.size() != 0) {
            Collections.sort(list, new Comparator<Carpool>() {
                @Override
                public int compare(Carpool lhs, Carpool rhs) {
                    return compareAdvancedSort(lhs, rhs);
                }
            });
            populateListView();
            registerClickCallback();
        }
    }

    private void sortWalkingDistance() {
        if(list != null && list.size() != 0) {
            Collections.sort(list, new Comparator<Carpool>() {
                @Override
                public int compare(Carpool lhs, Carpool rhs) {
                    return compareWalkingDistance(lhs, rhs);
                }
            });
            populateListView();
            registerClickCallback();
        }
    }

    private void sortDepartureTime() {
        if(list != null && list.size() != 0) {
            Collections.sort(list, new Comparator<Carpool>() {
                @Override
                public int compare(Carpool lhs, Carpool rhs) {
                    return compareDepartureTime(lhs, rhs);
                }
            });
            populateListView();
            registerClickCallback();
        }
    }

    private void sortDriverRating() {
        if(list != null && list.size() != 0) {
            Collections.sort(list, new Comparator<Carpool>() {
                @Override
                public int compare(Carpool lhs, Carpool rhs) {
                    return compareDriverRating(lhs, rhs);
                }
            });
            populateListView();
            registerClickCallback();
        }
    }

    private void sortCarpoolPrice() {
        if(list != null && list.size() != 0) {
            Collections.sort(list, new Comparator<Carpool>() {
                @Override
                public int compare(Carpool lhs, Carpool rhs) {
                    return compareCarpoolPrice(lhs, rhs);
                }
            });
            populateListView();
            registerClickCallback();
        }
    }

    private void longDistanceFilter() {
        if(list != null && list.size() != 0) {
            for(int i = 0; i < list.size(); i++) {
                double walk_distance_depart = getWalkDistance(search_depart_lat, search_depart_lng, list.get(i).getDepart_lat(), list.get(i).getDepart_lng());
                double walk_distance_desti = getWalkDistance(search_desti_lat, search_desti_lng, list.get(i).getDesti_lat(), list.get(i).getDesti_lng());
                if(walk_distance_depart + walk_distance_desti > 0.5) {
                    list.remove(i);
                }
            }
        }
    }

}
