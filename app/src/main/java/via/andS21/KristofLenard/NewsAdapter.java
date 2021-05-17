package via.andS21.KristofLenard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import via.andS21.KristofLenard.Persistence.WebClient;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    //TODO: possible - implement method to open news articles directly

    private NewsViewModel newsViewModel;

    public NewsAdapter(NewsViewModel viewModel)
    {
        newsViewModel = viewModel;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        if(newsViewModel.getNewsImageURLs().getValue().size() >= position) {
            holder.newsImage.setImageDrawable(WebClient.LoadImageFromWebOperations(newsViewModel.getNewsImageURLs().getValue().get(position)));
        }
        if(newsViewModel.getNewsTitle().getValue().size() >= position) {
            holder.newsTitle.setText(newsViewModel.getNewsTitle().getValue().get(position));
        }
    }

    @Override
    public int getItemCount() {
        return newsViewModel.getNewsTitle().getValue().size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        private final TextView newsTitle;
        private final ImageView newsImage;

        NewsViewHolder(View itemView){
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsImage = itemView.findViewById(R.id.newsImage);
        }
    }
}
