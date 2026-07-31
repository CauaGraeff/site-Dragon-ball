package Modelos;

import java.util.List;

public class Titulo {
    private String nome;
    private String ki;
    private String maxKi;
    private String race;
    private String image;

    public Titulo(List<TituloRecord> records) {
        if (records == null || records.isEmpty()) {
            throw new IllegalArgumentException("Lista de registros não pode estar vazia");
        }
        
        TituloRecord first = records.get(0);
        this.nome = first.name();
        this.ki = first.ki();
        this.maxKi = first.maxKi();
        this.race = first.race();
        this.image = first.image();
    }

    public String getNome() {
        return nome;
    }

    public String getKi() {
        return ki;
    }

    public String getMaxKi() {
        return maxKi;
    }

    public String getRace() {
        return race;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Character{" +
                "nome='" + nome + '\'' +
                ", ki='" + ki + '\'' +
                ", maxKi='" + maxKi + '\'' +
                ", race='" + race + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
