package Modelos;

import java.util.ArrayList;
import java.util.List;

public class Titulo {
    private String nome;
    private String ki;
    private String maxKi;
    private String race;
    private String image;

    public String getImage() {
        return image;
    }

    public String getMaxKi() {
        return maxKi;
    }

    public String getNome() {
        return nome;
    }

    public String getKi() {
        return ki;
    }

    public String getRace() {
        return race;
    }

    @Override
    public String toString() {
        return "Titulo{" +
                "nome='" + nome + '\'' +
                ", ki='" + ki + '\'' +
                ", maxKi='" + maxKi + '\'' +
                ", race='" + race + '\'' +
                '}';
    }

    public Titulo(List<TituloRecord> titulo) {
        List<String> atributosSeparados = new ArrayList<>();
        for(TituloRecord atributo : titulo) {
            atributosSeparados.add(atributo.name());
            atributosSeparados.add(atributo.ki());
            atributosSeparados.add(atributo.maxKi());
            atributosSeparados.add(atributo.race());
            atributosSeparados.add(atributo.image());
        }
        this.nome = atributosSeparados.get(0);
        this.ki = atributosSeparados.get(1);
        this.maxKi = atributosSeparados.get(2);
        this.race = atributosSeparados.get(3);
        this.image = atributosSeparados.get(4);
    }
}
