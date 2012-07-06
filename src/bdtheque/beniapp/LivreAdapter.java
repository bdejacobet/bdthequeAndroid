package bdtheque.beniapp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class LivreAdapter extends BaseAdapter {

	List<Livre> biblio;
	
	LayoutInflater inflater;
	
	public LivreAdapter(Context context,List<Livre> biblio) {
		inflater = LayoutInflater.from(context);
		this.biblio = biblio;
	}

	public int getCount() {
		return biblio.size();
	}

	public Object getItem(int position) {
		return biblio.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.itemlivre, null);
			holder.titreLivre = (TextView)convertView.findViewById(R.id.titreLivre);
			holder.serieLivre = (TextView)convertView.findViewById(R.id.serieLivre);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.titreLivre.setText(biblio.get(position).getNumero() + " - " + biblio.get(position).getTitre());
		holder.serieLivre.setText(biblio.get(position).getSerie());
		return convertView;
	}
	private class ViewHolder {
		TextView titreLivre;
		TextView serieLivre;
	}
}
