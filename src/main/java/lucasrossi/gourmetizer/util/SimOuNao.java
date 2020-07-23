package lucasrossi.gourmetizer.util;

public enum SimOuNao {
    SIM(0), NAO(1);

    private Integer codigo;

    private SimOuNao(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static SimOuNao getByCodigo(Integer codigo) {
        for (SimOuNao simOuNao : SimOuNao.values()) {
            if (simOuNao.getCodigo().equals(codigo)) {
                return simOuNao;
            }
        }
        return null;
    }

}