package science.anthonyalves.twitchkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.stream_row.view.*
import science.anthonyalves.twitchkotlin.R
import science.anthonyalves.twitchkotlin.models.Stream
import java.util.*

class StreamAdapter : RecyclerView.Adapter<StreamAdapter.ViewHolder>() {

    internal var mData = ArrayList<Stream>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindStream(stream: Stream) {
            with(stream) {
                Picasso.with(itemView.context).load(stream.imageUrl).into(itemView.mImageHolder)
                itemView.mChannel.text = stream.channelName
                itemView.mTitle.text = stream.title
                itemView.mViewers.text = stream.viewers.toString()
                itemView.mGame.text = stream.game
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stream_row, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: StreamAdapter.ViewHolder, position: Int) {
        holder.bindStream(mData[position])

    }

    override fun onViewDetachedFromWindow(holder: ViewHolder?) {
        holder?.itemView?.mImageHolder?.setImageDrawable(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: ViewHolder?) {
        holder?.itemView?.mImageHolder?.setImageDrawable(null)
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun swapData(newData: ArrayList<Stream>) {
        mData.clear()
        mData.addAll(newData)
        notifyDataSetChanged()
    }

    fun add(newData: ArrayList<Stream>) {
        mData.addAll(newData)
        notifyDataSetChanged()
    }
}
