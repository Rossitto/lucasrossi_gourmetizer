package lucasrossi.gourmetizer.service;

import java.util.LinkedHashSet;

import lucasrossi.gourmetizer.model.Prato;

/**
 * Classe de serviço e acesso a dados de pratos
 * 
 * @author Lucas Rossi
 * 
 */
public class PratoService {

    private static LinkedHashSet<Prato> pratos = new LinkedHashSet<Prato>();

    /**
     * Adiciona primeiro item à lista de pratos
     */
    static {
        pratos.add(new Prato("Lasanha", "Massa"));
    }

    /**
     * Obtém a lista de pratos
     * 
     * @return
     */
    public LinkedHashSet<Prato> obterPratos() {
        return pratos;
    }

    /**
     * Salva um novo prato
     * 
     * @param prato
     */
    public static void salvar(Prato prato) {
        pratos.add(prato);
    }

}