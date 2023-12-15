package com.example.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapp.R
import com.example.todoapp.databinding.TodoItemRowBinding
import com.example.todoapp.entities.TodoModel
import com.example.todoapp.utils.getFormattedDateTime
import com.example.todoapp.utils.priority_high
import com.example.todoapp.utils.priority_low
import com.example.todoapp.utils.priority_normal
import com.example.todoapp.utils.todo_delete
import com.example.todoapp.utils.todo_edit

//(action call back) for box click implement,delete option
class TodoAdapter(val actionCallback:(TodoModel, String) ->Unit)  :ListAdapter<TodoModel, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    class TodoViewHolder(
        private  val binding: TodoItemRowBinding,
        val actionCallback:(TodoModel, String) ->Unit //for edittodo
        ) : ViewHolder(binding.root) {

        fun bind(todoModel: TodoModel){

            binding.todo = todoModel

//            binding.rowTodoName.text =todoModel.name  //use when 'object_binding' don't need!

            //use when 'object_binding' don't need!\\
//            binding.rowDateTimeTv.text ="${getFormattedDateTime(todoModel.date,"dd/MM/yyyy")} ${getFormattedDateTime(todoModel.time,"hh:mm: a")}"


//            binding.rowTodoCompleteBox.isChecked = todoModel.isCompleted  //use when 'object_binding' don't need!

            //for object binding use in xml or use this option\\  //(image button)
            /*
            val iconId = when (todoModel.priority){
            priority_low -> R.drawable.baseline_blue_star_24
            priority_normal -> R.drawable.baseline_green_star_24
            else ->  R.drawable.baseline_red_star_24
            }
            */

//            binding.rowTodoPriorityIcon.setImageResource(iconId)  //use when 'object_binding' don't need!

            //for box click implement
            binding.rowTodoCompleteBox.setOnClickListener {

                actionCallback(todoModel, todo_edit)

            }

            //for option delete ( pop up menu show)
            binding.rowMenuIcon.setOnClickListener {
                val popupMenu = PopupMenu(it.context,it)
                val inflater = popupMenu.menuInflater
                inflater.inflate(R.menu.todo_row_menu, popupMenu.menu)
                popupMenu.show()

                //for action purpose(pop up menu
                popupMenu.setOnMenuItemClickListener {item ->

                    when (item.itemId) {
                        R.id.item_delete -> {

                            actionCallback(todoModel, todo_delete)

                            true
                        }
                        else ->false
                    }


                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return TodoViewHolder(binding, actionCallback)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todoModel = getItem(position)
        holder.bind(todoModel)
    }

}

class TodoDiffCallback : DiffUtil.ItemCallback<TodoModel>() {
    override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return oldItem == newItem
    }

}