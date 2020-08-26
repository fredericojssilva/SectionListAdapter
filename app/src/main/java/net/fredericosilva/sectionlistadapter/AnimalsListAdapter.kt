package net.fredericosilva.sectionlistadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.animal_item.view.*
import kotlinx.android.synthetic.main.animal_item_header.view.*

class AnimalsListAdapter :
    SectionListAdapter<Animal, AnimalItemViewHolder, AnimalHeaderViewHolder>() {
    override fun createViewHolderForSectionItem(
        parent: ViewGroup?,
        viewType: Int
    ): AnimalItemViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.animal_item, parent, false)
        return AnimalItemViewHolder(view)
    }

    override fun bindViewHolderForSectionItem(
        holder: AnimalItemViewHolder,
        item: Animal?,
        sectionId: Int
    ) {
        holder.itemView.animal_breed.text = item?.brand
    }

    override fun createViewHolderForSectionHeader(
        parent: ViewGroup?,
        viewType: Int
    ): AnimalHeaderViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.animal_item_header, parent, false)
        return AnimalHeaderViewHolder(view)
    }

    override fun bindViewHolderForSectionHeader(
        holder: AnimalHeaderViewHolder,
        header: Section.SectionHeader?,
        isFirst: Boolean
    ) {
        holder.itemView.animal_category.text = header?.headerTitle
    }
}

class AnimalHeaderViewHolder(v: View) : RecyclerView.ViewHolder(v)
class AnimalItemViewHolder(v: View) : RecyclerView.ViewHolder(v)