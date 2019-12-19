package com.example.imdbtopratedmovies.views.home_screen



import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbtopratedmovies.R
import com.example.imdbtopratedmovies.views.models.Movie
import com.example.imdbtopratedmovies.views.network.IMAGE_BASE_URL
import com.example.imdbtopratedmovies.views.network.IMAGE_SIZE
import com.example.imdbtopratedmovies.views.views.web_view_screen.WebViewActivity
import com.example.imdbtopratedmovies.views.views.home_screen.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home_sreen.*


/**
 * This is the Home activity which shows list of movies
 */
const val IMDB_ID = "imdb_id"

class HomeSreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_sreen)
        val gridLayoutManager = GridLayoutManager(this,2)
        val mViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        RV_home.layoutManager = gridLayoutManager
        val rv_adapter = RVadapter(this)
        RV_home.adapter =rv_adapter

        mViewModel.loadData()
        mViewModel.observableErrorMessage.observe(this,Observer{
            Snackbar.make(root_layout,it,Snackbar.LENGTH_SHORT).show()
        })
        mViewModel.observableMovieResponse.observe(this,Observer{
              rv_adapter.submitList(it)
        })


    }



    class RVadapter(val context: Context): ListAdapter<Movie, RVadapter.ViewHolder>(diffCallback) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_design,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.apply{

              Picasso.with(context).load(IMAGE_BASE_URL+ IMAGE_SIZE+getItem(position).poster).placeholder(R.drawable.error)
                  .error(R.drawable.error).into(IV_poster)
              TVtitleAndRelease.text = getItem(position).title+" ("+getItem(position).run { release_date.substringBefore('-') }+")"
              TVtitleAndRelease.isSelected = true
              TVrating.text = getItem(position).rating.toString()+"/10"
              RB_rating.rating = (getItem(position).rating/2).toFloat()
              itemsView.setOnClickListener {
                 val intent = Intent(context,
                     WebViewActivity::class.java)
                  intent.putExtra(IMDB_ID,getItem(position).imdb_id)
                  context.startActivity(intent)
              }

          }

        }

        companion object {
            val diffCallback = object : DiffUtil.ItemCallback<Movie>(){
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.title==newItem.title
                }

                override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.title==newItem.title
                }

            }
        }


        class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            val IV_poster = view.findViewById<ImageView>(R.id.IV_poster)
            val TVtitleAndRelease = view.findViewById<TextView>(R.id.TV_title_and_release)
            val TVrating = view.findViewById<TextView>(R.id.TV_rating)
            val RB_rating = view.findViewById<RatingBar>(R.id.RB_rating)
            val itemsView = view
        }
    }

}
