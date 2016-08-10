package info.devexchanges.expandablelayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private Animation animationUp, animationDown;
    private Context context;

    public RecyclerAdapter(Context context, Animation animationUp, Animation animationDown) {
        this.layoutInflater = LayoutInflater.from(context);
        this.animationDown = animationDown;
        this.animationUp = animationUp;
        this.context = context;
    }

    @Override
    public ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_recycler_view, parent, false);

        return new ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ReyclerViewHolder holder, int position) {
        if (position % 3 == 0) {
            holder.image.setImageResource(R.drawable.girl_1);
        } else if (position % 3 == 1) {
            holder.image.setImageResource(R.drawable.girl_2);
        } else {
            holder.image.setImageResource(R.drawable.girl_3);
        }

        holder.showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.contentLayout.isShown()) {
                    holder.contentLayout.setVisibility(View.GONE);
                    holder.contentLayout.startAnimation(animationUp);

                    holder.showMore.setText(context.getString(R.string.show));
                    holder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
                } else {
                    holder.contentLayout.setVisibility(View.VISIBLE);
                    holder.contentLayout.startAnimation(animationDown);

                    holder.showMore.setText(context.getString(R.string.hide));
                    holder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView showMore;
        private View contentLayout;

        private ReyclerViewHolder(final View v) {
            super(v);

            image = (ImageView) v.findViewById(R.id.image);
            contentLayout = v.findViewById(R.id.content);
            showMore = (TextView) v.findViewById(R.id.show_more);
        }
    }
}
