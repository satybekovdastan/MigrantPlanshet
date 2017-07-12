package com.migrate.admin.pagination.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.migrate.admin.pagination.Activities.SecondActivities.HTSecondActivity;
import com.migrate.admin.pagination.Activities.SecondActivities.RulesOfMigrationActivity;
import com.migrate.admin.pagination.Helpers.OnLoadMoreListener;
import com.migrate.admin.pagination.R;
import com.migrate.admin.pagination.Serializables.RulesOfIncoming;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RVProhibitionAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<RulesOfIncoming> studentList;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;
    public Context context;
    int a;
    String name;

    public RVProhibitionAdapter(List<RulesOfIncoming> students, RecyclerView recyclerView, Context context, int a, String name) {
        studentList = students;
        this.name = name;
        this.a = a;
        this.context = context;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore();
                                }
                                loading = true;
                            }
                        }
                    });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return studentList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_rv_rules_of_incoming, parent, false);

            vh = new StudentViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progressbar_item, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StudentViewHolder) {

            RulesOfIncoming singleStudent = (RulesOfIncoming) studentList.get(position);

            ((StudentViewHolder) holder).tvName.setText(singleStudent.getTitle());
           // Picasso.with(context).load(singleStudent.getImage()).into(((StudentViewHolder) holder).imageView);
            Picasso.with(context).load(Integer.parseInt(singleStudent.getImage())).into(((StudentViewHolder) holder).imageView);
            ((StudentViewHolder) holder).student = singleStudent;

        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


    //
    public class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        ImageView imageView;
        public RulesOfIncoming student;

        public StudentViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tv_rules_of_incoming);
            imageView = (ImageView) v.findViewById(R.id.image_rules_of_incoming);
            imageView.setVisibility(View.VISIBLE);
            v.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.e("TAG_RF_PRO", "asdasdasdas");
                    Intent intent;
                    if (a == 1)
                        intent = new Intent(context, HTSecondActivity.class);
                    else intent = new Intent(context, RulesOfMigrationActivity.class);
                    intent.putExtra("text", student.getText());
                    intent.putExtra("name", name);
                    context.startActivity(intent);

                }
            });
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);

        }
    }
}