package org.genadidharma.jjjyuk.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListSpacingItemDecoration extends RecyclerView.ItemDecoration {
    int verticalSpaceSize, horizontalSpace;

    public ListSpacingItemDecoration(int verticalSpaceSize, int horizontalSpaceSize) {
        this.verticalSpaceSize = verticalSpaceSize;
        this.horizontalSpace = horizontalSpaceSize;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int itemCount = state.getItemCount();

        outRect.left = horizontalSpace;
        outRect.right = horizontalSpace;
        outRect.bottom = verticalSpaceSize;

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = verticalSpaceSize * 2;
            outRect.bottom = verticalSpaceSize;
        }else if(parent.getChildAdapterPosition(view) == itemCount - 1){
            outRect.bottom = verticalSpaceSize * 2;
        }
    }


}
