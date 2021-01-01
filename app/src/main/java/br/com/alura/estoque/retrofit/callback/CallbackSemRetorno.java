package br.com.alura.estoque.retrofit.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import static br.com.alura.estoque.retrofit.callback.MensagensCallback.MESSAGEM_ERRO_FALHA_DE_COMUNICACAO;
import static br.com.alura.estoque.retrofit.callback.MensagensCallback.MESSAGEM_ERRO_RESPOSTA_NAO_RECEBIDA;

public class CallbackSemRetorno implements Callback<Void> {

    private final RespostaCallback callback;

    public CallbackSemRetorno(RespostaCallback callback) {
        this.callback = callback;
    }

    @Override
    @EverythingIsNonNull
    public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.isSuccessful()){
            callback.quandoSucesso();
        } else {
            callback.quandoFalha(MESSAGEM_ERRO_RESPOSTA_NAO_RECEBIDA);
        }
    }

    @Override
    @EverythingIsNonNull
    public void onFailure(Call<Void> call, Throwable t) {
        callback.quandoFalha(MESSAGEM_ERRO_FALHA_DE_COMUNICACAO + t.getMessage());
    }

    public interface  RespostaCallback {
        void quandoSucesso();
        void quandoFalha(String erro);
    }
}
