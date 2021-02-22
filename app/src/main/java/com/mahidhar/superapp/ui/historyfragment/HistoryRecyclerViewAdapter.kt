package com.mahidhar.superapp.ui.historyfragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mahidhar.superapp.R
import com.mahidhar.superapp.enum.TransactionType
import com.mahidhar.superapp.model.MicroApp
import com.mahidhar.superapp.model.Transaction
import com.mahidhar.superapp.ui.homefragment.featured.FeaturedRecyclerViewAdapter
import com.mahidhar.superapp.utils.IconUtil

class HistoryRecyclerViewAdapter(val transactionList: List<Transaction>): RecyclerView.Adapter<HistoryRecyclerViewAdapter.TransactionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        Log.i("Featured Recycler Adapter", transactionList.toString())
        val layout_inflator = LayoutInflater.from(parent.context)
        val view = layout_inflator.inflate(R.layout.history_rv_item, parent, false) ;
        return HistoryRecyclerViewAdapter.TransactionHolder(view);
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val transaction:Transaction = transactionList[position];
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    class TransactionHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var app_view:View = view
        var transaction:Transaction?=null
        val transactor:TextView = view.findViewById<TextView>(R.id.history_transactor)
        val amount:TextView = view.findViewById<TextView>(R.id.history_amount)
        val time:TextView = view.findViewById<TextView>(R.id.history_time)
        val image:ImageView = view.findViewById<ImageView>(R.id.history_icon)
        var symbol:String = "+"

        fun bind(transaction: Transaction){
            this.transaction = transaction
            this.transactor.setText(transaction.transactor)
            this.time.setText(transaction.time)
            if(transaction.type==0) {
                this.image.setImageResource(R.drawable.ic_remove_circle_black_18dp)
                this.image.setColorFilter(ContextCompat.getColor(app_view.context, R.color.red), android.graphics.PorterDuff.Mode.SRC_IN)
                this.symbol = "-"
            }else{
                this.image.setImageResource(R.drawable.ic_add_circle_black_18dp)
                this.image.setColorFilter(ContextCompat.getColor(app_view.context, R.color.green), android.graphics.PorterDuff.Mode.SRC_IN)
                this.symbol = "+"
            }
            this.amount.setText(symbol+transaction.amount.toString())
        }


        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }
}