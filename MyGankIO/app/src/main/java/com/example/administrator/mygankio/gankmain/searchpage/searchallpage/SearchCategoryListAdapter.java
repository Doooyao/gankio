package com.example.administrator.mygankio.gankmain.searchpage.searchallpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.customview.FootRecycleAdapter;
import com.example.administrator.mygankio.data.GankBean;
import com.example.administrator.mygankio.utils.StringUtils;
import com.example.administrator.mygankio.utils.WebUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdfz on 2017/10/17.
 */

public class SearchCategoryListAdapter extends FootRecycleAdapter {


    List<GankBean> gankBeanList;
    SearchCategoryContract.View fragment;
    Context context;
    public SearchCategoryListAdapter(SearchCategoryContract.View fragment) {
        super(fragment.takeContext());
        this.context = fragment.takeContext();
        gankBeanList = new ArrayList<>();
//        gankBeanList.add(new GankBean());
    }
    public List<GankBean> getGankBeanList() {
        return gankBeanList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            View v = LayoutInflater.from(context).inflate(R.layout.search_categorygank_item,parent,false);
            SearchAllViewHolder searchAllViewHolder = new SearchAllViewHolder(v);
            return searchAllViewHolder;
        }else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchAllViewHolder){
            final GankBean g = gankBeanList.get(position);
            SearchAllViewHolder searchAllViewHolder = (SearchAllViewHolder) holder;
            searchAllViewHolder.itemTitle.setText(g.getDesc());
            searchAllViewHolder.itemInfo.setText("via."+g.getWho()+"  "+g.getPublishedAt().substring(0,10));
            searchAllViewHolder.categoryDesc.setText(g.getType());
            searchAllViewHolder.categoryIcon.setImageResource(StringUtils.getImageResIdByCategoryString(g.getType()));
            searchAllViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebUtils.gotoWebActivity(context,g.getUrl(),v);
                }
            });
        }else {
            super.onBindViewHolder(holder, position);
        }
    }
    @Override
    public int getItemCount() {
        return super.addFootViewCount(gankBeanList.size());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class SearchAllViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView categoryDesc;
        TextView itemTitle;
        RelativeLayout relativeLayout;
        TextView itemInfo;
        public SearchAllViewHolder(View itemView) {
            super(itemView);
            categoryIcon = (ImageView) itemView.findViewById(R.id.search_all_listitem_categoryicon);
            categoryDesc = (TextView) itemView.findViewById(R.id.search_all_listitem_categorydesc);
            itemTitle = (TextView) itemView.findViewById(R.id.search_all_listitem_itemtitle);
            itemInfo = (TextView) itemView.findViewById(R.id.search_all_listitem_otherinfo);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.search_all_listitem_clickablelayout);
        }
    }
}
