package com.magic.heartburndiary.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magic.heartburndiary.R
import com.magic.heartburndiary.adapters.QuestionRecyclerAdapter
import com.magic.heartburndiary.models.QuestionModel


class QuestionFragment : Fragment() {

    private var recyclerViewQuestion: RecyclerView? = null
    private var textViewStatus: TextView? = null

    private val questionList = arrayListOf<QuestionModel>()

    private var cntPositiveAnswers: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createQuestionsList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewStatus = view.findViewById(R.id.textViewStatus)

        // getting the recyclerview by its id
        recyclerViewQuestion = view.findViewById(R.id.recyclerViewQuestion)
        // this creates a vertical layout Manager
        recyclerViewQuestion?.layoutManager = LinearLayoutManager(requireActivity())
        // This will pass the ArrayList to our Adapter
        val adapter = QuestionRecyclerAdapter(
            questionList,
            onItemSwitch = { isSwitch ->

                if (isSwitch) ++cntPositiveAnswers
                else --cntPositiveAnswers

                checkCountAnswers()

            }
        )
        // Setting the Adapter with the recyclerview
        recyclerViewQuestion?.adapter = adapter
    }

    private fun checkCountAnswers() {
        when (cntPositiveAnswers) {
            0 -> textViewStatus?.text = ""
            1 -> textViewStatus?.text = "Низкий риск развития/обострения ГЭРБ"
            2 -> textViewStatus?.text = "Средний риск развития/обострения ГЭРБ"
            3 -> textViewStatus?.text = "Высокий риск развития/обострения ГЭРБ"
            else -> textViewStatus?.text = "Высокий риск развития/обострения ГЭРБ"
        }
    }

    private fun createQuestionsList() {
        questionList.add(QuestionModel("У вас было более 2 эпизодов изжоги в неделю?", false))
        questionList.add(QuestionModel("У вас было более 1 сильных приступов изжоги?", false))
        questionList.add(QuestionModel("У вас была рвота?", false))
        questionList.add(QuestionModel("Вы чувствовали дискомфорт за грудиной?", false))
        questionList.add(QuestionModel("Изжога возникала ночью?", false))
        questionList.add(QuestionModel("Были ли эпизоды изжоги без связи с приемом пищи и воды?", false))
        questionList.add(QuestionModel("Были ли эпизоды изжоги вызваны провокаторами?", false))
        questionList.add(QuestionModel("Снизилось ли количество эпизодов изжоги, по сравнению с предыдущей неделей?", false))
        questionList.add(QuestionModel("Вы соблюдаете режим питания?", false))
        questionList.add(QuestionModel("Сколько раз вы принимали лекарства от изжоги?", false))
    }
}
