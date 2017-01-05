package science.anthonyalves.twitchkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.game_row.view.*
import science.anthonyalves.twitchkotlin.R
import science.anthonyalves.twitchkotlin.models.Game
import java.util.*

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    internal var mData = ArrayList<Game>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindGame(game: Game) {
            with(itemView) {
                Picasso.with(context).load(game.imageUrl).into(mImageHolder)
                mName.text = game.name
                mChannels.text = game.channels.toString()
                mViewers.text = game.viewers.toString()
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder?) {
        holder?.itemView?.mImageHolder?.setImageDrawable(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: ViewHolder?) {
        holder?.itemView?.mImageHolder?.setImageDrawable(null)
        super.onViewRecycled(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_row, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        holder.bindGame(mData[position])

    }

    override fun getItemCount(): Int {
        return mData.size
    }


    fun swapData(newData: ArrayList<Game>) {
        mData.clear()
        mData.addAll(newData)
        notifyDataSetChanged()
    }

    fun add(newData: ArrayList<Game>) {
        mData.addAll(newData)
        notifyDataSetChanged()
    }
}
