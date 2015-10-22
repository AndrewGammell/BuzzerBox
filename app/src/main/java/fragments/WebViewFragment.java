package fragments;

import abstracts.AbstractFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import io.buzzerbox.app.R;

public class WebViewFragment extends AbstractFragment {

    private static final String KEY = "URL";
    private WebView webView;
    private String url;


    @Override
    protected int getLayout() {
        return R.layout.layout_webview;
    }

    @Override
    protected void instantiateWidgets(View view) {
        webView = (WebView)view.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null){
            url = bundle.getString(KEY);
        }

    }


    public static WebViewFragment newInstance(int in){
        String url = null;
        switch(in){
            case 1: url = "http://guyforceshiswifetodressinagarbagebagforthenextthreeyears.com/";
                break;
            case 2: url = "https://www.youtube.com/watch?v=-PlzYOZWKxY";
                break;
            default: url = "http://www.donothingfor2minutes.com/";
                break;

        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY, url);
        WebViewFragment wf = new WebViewFragment();
        wf.setArguments(bundle);
        return wf;
    }
}
