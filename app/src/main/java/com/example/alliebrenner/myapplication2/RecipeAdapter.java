package com.example.alliebrenner.myapplication2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RecipeAdapter extends BaseAdapter {
    //every asapter takes app and a list of data to display
    private Context myContext;
    private List<Recipe> myRecipeList;
    private LayoutInflater myInflater;
    public Integer count;
    public Button starButton;

    //constructor,
    public RecipeAdapter(Context myContext, ArrayList<Recipe> myRecipeList){
        //initialize instance var
        this.myContext = myContext;
        this.myRecipeList = myRecipeList;
        myInflater = (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public int getCount(){
        //gives nuber of recipes in data source
        count = myRecipeList.size();
        return count;


    }

    @Override
    public Object getItem(int position){
        //returns item at specific position in the data source
        return myRecipeList.get(position);
    }

    //returns row id associated w the specific position in the list
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        //check if view already exists
        //if yes, then no need to inflate and findViewbyID again
        if (convertView == null){
            //inflate
            convertView = myInflater.inflate(R.layout.recipe_detail,parent,false);
            // add views to the holder
            holder = new ViewHolder();
            //views
            holder.titleTextView = convertView.findViewById(R.id.recipe_title);
            holder.servingTextView = convertView.findViewById(R.id.serving_size);
            holder.thumbnailImageView = convertView.findViewById(R.id.recipe_image);
            holder.cookingTimeTextView = convertView.findViewById( R.id.cooking_time);
            holder.starImageButton = convertView.findViewById(R.id.imageButton);
            //holder.resultsFoundtextView = convertView.findViewById(R.id.results_found);

            //add the holder to the view
            convertView.setTag(holder);
        }
        else{
            //get the view holder from convertview
            holder = (ViewHolder)convertView.getTag();
        }
        //get relevant subviews of the row view
        final TextView titleTextView = holder.titleTextView;
        TextView servingTextView = holder.servingTextView;
        TextView cookingTimeTextView = holder.cookingTimeTextView;
        //TextView resultsFoundtextView = holder.resultsFoundtextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;
        ImageButton starImageButton = holder.starImageButton;

        //get corresponding recipe for each row
        final Recipe recipe = (Recipe)getItem(position);

        starImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
                notificationIntent.setData(Uri.parse(recipe.instructionUrl));

                String message = "The Instruction for "+ recipe.title + " can be found here!";

                PendingIntent pi = PendingIntent.getActivity(myContext, 0, notificationIntent, 0);
                // Resources r = getResources();
                Notification notification = new NotificationCompat.Builder(myContext, "channel_ID")
                        .setTicker("Cooking Instructions")
                        .setSmallIcon(android.R.drawable.ic_menu_report_image)
                        .setContentTitle("Cooking Instructions")
                        .setContentText(message)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .build();

                NotificationManager notificationManager2 =  (NotificationManager) myContext.getSystemService(Service.NOTIFICATION_SERVICE);
                notificationManager2.notify(0, notification);

            }
        });

        //update the row views textviews and imageview to display the info
        //titleTextView
        titleTextView.setText(recipe.title);


        //servingTextView
        servingTextView.setText(recipe.servings + " servings");

        //cookingTimeTextView
        cookingTimeTextView.setText(recipe.prepTime );


        //resultsfoundTextView
        //resultsFoundtextView.setText(count);

        Picasso.with(myContext).load(recipe.imageUrl).into(thumbnailImageView);

        return convertView;


    }

    //view holder
    //used to customize what you want to put into the view
    //depends on the layout design of the row
    //private static class u have to define
    private static class ViewHolder{
        public TextView titleTextView;
        public TextView servingTextView;
        public TextView cookingTimeTextView;
        // public TextView resultsFoundtextView;
        public ImageView thumbnailImageView;
        public ImageButton starImageButton;
    }

}

