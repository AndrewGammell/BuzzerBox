package adapter;

/**
 * Created by Devstream on 29/09/2015.
 */
//public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.PersonHolder> {
//
//    private List<T> list;
//    private ListFragment fragment;
//
//
//    public CustomAdapter(List<T> list, ListFragment fragment) {
//        this.list = list;
//        this.fragment = fragment;
//
//    }
//
//    @Override
//    public PersonHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
//
//        return new PersonHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(PersonHolder personHolder, int i) {
//        personHolder.icon1.setImageResource(R.mipmap.station_icon);
//        personHolder.icon2.setImageResource(R.mipmap.transport_icon);
//        personHolder.name.setText(list.get(i).getName());
//        personHolder.bikes.setText(String.valueOf(list.get(i).getBikes()));
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class PersonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private ImageView icon1;
//        private ImageView icon2;
//        private TextView name;
//        private TextView bikes;
//
//        public PersonHolder(View view) {
//            super(view);
//            view.setOnClickListener(this);
//            icon1 = (ImageView) view.findViewById(R.id.image_view1);
//            icon2 = (ImageView) view.findViewById(R.id.image_view2);
//            name = (TextView) view.findViewById(R.id.name);
//            bikes = (TextView) view.findViewById(R.id.bikes);
//        }
//
//        @Override
//        public void onClick(View view) {
//            int index = getAdapterPosition();
//
//        }
//
//    }
//}
