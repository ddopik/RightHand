package presenter.Adapters;


import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ddopik.scopelistner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;
import model.tabels.SingleItem;
import presenter.FragmentOnePresenter;

/**
 * Created by ddopik on 10/12/2017.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.SingleItem_ViewHolder> {

    private RealmResults<SingleItem> itemsList;
    private Context context;
    private Fragment fragmentContext;
    private FragmentOnePresenter fragmentOnePresenter;
    private int openExistingItemCode =11;

    public ItemsAdapter(RealmResults<SingleItem> singleItems, Context context,Fragment fragment) {
        this.itemsList = singleItems;
        this.context = context;
        fragmentContext=fragment;
    }

    public ItemsAdapter(Context context) {

        this.context = context;
    }

    public void setItemsList(RealmResults<SingleItem> singleItems) {
        this.itemsList = singleItems;
    }

    @Override
    public SingleItem_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_row, parent, false);
        fragmentOnePresenter = new FragmentOnePresenter(context);
        return new SingleItem_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SingleItem_ViewHolder holder, int position) {
        SingleItem singleItem = itemsList.get(position);
        holder.item_id.setText(singleItem.getItemId() + "");
        holder.item_name.setText(singleItem.getItemName());
        holder.item_price.setText(singleItem.getItemPrice());
        holder.item_existence.setText(singleItem.isItemExistence() + "");
        holder.item_last_update.setText(singleItem.getItemUpdate());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class SingleItem_ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_id)
        public TextView item_id;
        @BindView(R.id.item_name)
        public TextView item_name;
        @BindView(R.id.item_price)
        public TextView item_price;
        @BindView(R.id.item_existence)
        public TextView item_existence;
        @BindView(R.id.item_last_update)
        public TextView item_last_update;
        @BindView(R.id.single_item_row)
        public View View;

        public SingleItem_ViewHolder(View view) {
            super(view);
            try {
                ButterKnife.bind(this, view);
            } catch (Exception e) {
                Log.e("Item Adapter", "Error---->" + e.getMessage());
            }


        }

        @OnClick(R.id.single_item_row)
        public void singleRowEvent() {
            fragmentOnePresenter.launchSingleItemDialogFragment(Integer.parseInt(item_id.getText().toString(),openExistingItemCode),openExistingItemCode );

        }


    }


}
