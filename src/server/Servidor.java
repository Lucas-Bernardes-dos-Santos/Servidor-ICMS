package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private ServerSocket sckServidor;

	public Servidor() throws IOException {
		this.sckServidor = new ServerSocket(4000);

		for (;;) {
			Socket sckEcho;
			InputStream canalEntrada;
			OutputStream canalSaida;
			BufferedReader entrada;
			PrintWriter saida;

			sckEcho = this.sckServidor.accept();
			canalEntrada = sckEcho.getInputStream();
			canalSaida = sckEcho.getOutputStream();
			entrada = new BufferedReader(new InputStreamReader(canalEntrada));
			saida = new PrintWriter(canalSaida, true);

			while (true) {
				String pedido = entrada.readLine();

				if (pedido == null || pedido.length() == 0)
					break;
				
				switch (pedido.toLowerCase()) {
				case "arroz": {
					pedido = "7";
					break;
				}
				case "pao": {
					pedido = "12";
					break;
				}
				case "material escolar": {
					pedido = "18";
					break;
				}
				case "gasolina": {
					pedido = "7";
					break;
				}
				case "energia eletrica": {
					pedido = "7";
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + pedido);
				}

				saida.println(pedido);
			}
			sckEcho.close();
		}
	}
}
