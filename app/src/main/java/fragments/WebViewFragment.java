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

    private WebView webView;
    private String url = "http://guyforceshiswifetodressinagarbagebagforthenextthreeyears.com/";


    @Override
    protected int getLayout() {
        return R.layout.layout_webview;
    }

//    creates the view and loads the url.
    @Override
    protected void instantiateWidgets(View view) {
        webView = (WebView) view.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

  // returns a new fragment
    public static WebViewFragment newInstance() {
        WebViewFragment wf = new WebViewFragment();
        return wf;
    }
}
