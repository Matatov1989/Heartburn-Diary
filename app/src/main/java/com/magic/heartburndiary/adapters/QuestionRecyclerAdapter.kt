package com.magic.heartburndiary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.magic.heartburndiary.R
import com.magic.heartburndiary.models.QuestionModel

class QuestionRecyclerAdapter(
    private val questionList: ArrayList<QuestionModel>,
    private var onItemSwitch: ((Boolean) -> Unit)? = null
) : RecyclerView.Adapter<QuestionRecyclerAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewQuestion: TextView
        val switchAnswer: Switch

        init {
            // Define click listener for the ViewHolder's View
            textViewQuestion = view.findViewById(R.id.textViewQuestion)
            switchAnswer = view.findViewById(R.id.switchAnswer)


        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.element_question, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textViewQuestion.text = questionList[position].question
        viewHolder.switchAnswer.isChecked = questionList[position].answer


        viewHolder.switchAnswer.setOnCheckedChangeListener { buttonView, isChecked ->
            onItemSwitch?.invoke(isChecked)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = questionList.size
}
