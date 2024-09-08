package entities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class ServerMetricsGUI extends JFrame {
    private DefaultCategoryDataset dataset;
    private JTextArea requestLog;

    public ServerMetricsGUI() {
        setTitle("Servidor Web - Monitoramento de Desempenho");
        setSize(800, 600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        requestLog = new JTextArea();
        requestLog.setEditable(false);
        requestLog.setFont(new Font("Courier New", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(requestLog);

        dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createLineChart(
                "Métricas do Servidor",
                "Tempo",
                "Métricas",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 300));

        add(scrollPane, BorderLayout.CENTER);
        add(chartPanel, BorderLayout.SOUTH);

        // Atualização periódica de dados para o gráfico
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Simulação de dados atualizados
                updateMetrics(Math.random() * 10, Math.random() * 20);
            }
        }, 0, 1000);
    }

    public synchronized void appendRequest(String clientAddress, String requestDetails) {
        requestLog.append("===== Nova Requisição =====\n");
        requestLog.append("Cliente: " + clientAddress + "\n");
        requestLog.append("Detalhes:\n" + requestDetails + "\n");
        requestLog.append("----------------------------\n");
        requestLog.setCaretPosition(requestLog.getDocument().getLength());
    }

    public synchronized void updateMetrics(double avgWaitTime, double cpuUsage) {
        dataset.addValue(avgWaitTime, "Tempo Médio de Espera", Long.toString(System.currentTimeMillis()));
        dataset.addValue(cpuUsage, "Uso de CPU", Long.toString(System.currentTimeMillis()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ServerMetricsGUI gui = new ServerMetricsGUI();
            gui.setVisible(true);
        });
    }
}
