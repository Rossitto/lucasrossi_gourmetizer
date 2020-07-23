package lucasrossi.gourmetizer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lucasrossi.gourmetizer.model.Prato;
import lucasrossi.gourmetizer.service.PratoService;
import lucasrossi.gourmetizer.util.SimOuNao;

public class ButtonActionListener implements ActionListener {

    private PratoService pratoService = new PratoService();
    private Prato pratoSelecionado = null;

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Prato prato : pratoService.obterPratos()) {
            this.pratoSelecionado = prato;

            SimOuNao existeAdjetivo = exibePergunta(prato.getAdjetivo());

            if (existeAdjetivo == SimOuNao.SIM) {
                checkPergunta(exibePergunta(prato.getNome()));
                return;
            }

        }

        exibePerguntaBoloChocolate();

    }

    private void exibePerguntaBoloChocolate() {
        Prato boloDeChocolate = new Prato("Bolo de chocolate");

        this.pratoSelecionado = boloDeChocolate;

        checkPergunta(exibePergunta(boloDeChocolate.getNome()));
    }

    private void checkPergunta(SimOuNao simOuNao) {
        if (simOuNao == SimOuNao.SIM) {
            exibeMensagemSucesso();
        } else if (simOuNao == SimOuNao.NAO) {
            criaNovoPrato();
        }
    }

    private SimOuNao exibePergunta(String pratoInfo) {
        return SimOuNao.getByCodigo(JOptionPane.showConfirmDialog(null,
                "O prato que você pensou é ".concat(pratoInfo).concat("?"), "Confirm", JOptionPane.YES_NO_OPTION));
    }

    private void exibeMensagemSucesso() {
        JOptionPane.showMessageDialog(null, "Acertei de novo!");
    }

    private void criaNovoPrato() {
        // String nome = JOptionPane.showInputDialog("Em qual prato você pensou?");
        String nome = JOptionPane.showInputDialog(null, "Em qual prato vocë pensou?", "Desisto",
                JOptionPane.QUESTION_MESSAGE);

        if (nome == null || nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Eita! Prato não informado. =(", "Confirm", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String adjetivo = JOptionPane.showInputDialog(
                nome.concat(" é _______, mas ").concat(this.pratoSelecionado.getNome()).concat(" não"));

        if (adjetivo == null || adjetivo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Adjetivo do prato não informado", "Confirm",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        PratoService.salvar(new Prato(nome, adjetivo));

    }

}