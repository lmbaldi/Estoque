package br.com.alura.estoque.retrofit.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import static br.com.alura.estoque.retrofit.callback.MensagensCallback.MESSAGEM_ERRO_FALHA_DE_COMUNICACAO;
import static br.com.alura.estoque.retrofit.callback.MensagensCallback.MESSAGEM_ERRO_RESPOSTA_NAO_RECEBIDA;

public class CallbackComRetorno<T> implements Callback<T> {


    private final RespostaCallback<T> callback;

    public CallbackComRetorno(RespostaCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    @EverythingIsNonNull
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()){
            T resultado = response.body();
            if(resultado != null){
                callback.quandoSucesso(resultado);
            }else {
                callback.quandoFalha(MESSAGEM_ERRO_RESPOSTA_NAO_RECEBIDA);
            }
        }

    }

    @Override
    @EverythingIsNonNull
    public void onFailure(Call call, Throwable t) {
        callback.quandoFalha(MESSAGEM_ERRO_FALHA_DE_COMUNICACAO + ": " + t.getMessage());
    }

    public interface  RespostaCallback <T>{
        void quandoSucesso(T resultdo);
        void quandoFalha(String erro);
    }
}
