#include <iostream>
#include <string>
#include "imagem.hpp"
#include "pgm.hpp"
#include "ppm.hpp"
using namespace std;

void tipo_erro(int erro);//imprime os tipos de erro

int main(){
	Imagem *imagem1;
	PGM *pgm1;
	PPM *ppm1;
	string path_in, path_out;
	char  opcao, tipo;
	
	while(opcao != '0'){
		cout<<"(1)-> Copiar Imagem"<<endl;
		cout<<"(2)-> Decifrar Mensagem de Imagem"<<endl;
		cout<<"(0)-> Fechar Programa"<<endl;
		cout<<"Digite uma opção: ";
		cin>>opcao;
		cin.ignore(256, '\n');//ignora ate 256 caracteres invalidos
		
		switch (opcao){
			case '1':
				imagem1 = new Imagem(); //instancia da classe imagem
				cout<<"Digite o caminho da imagem: ";
				try{
					getline(cin,path_in);
					imagem1->lerImagem(path_in);
				}
				catch(int erro){
					tipo_erro(erro);
					break;
				}
				
				try{
					cout<<"Digite o caminho para criar a cópia da imagem: ";
					getline(cin,path_out);
				}
				catch(int erro){
					tipo_erro(erro);
					break;
				}
				
				
				imagem1->gravarImagem(path_out);
				cout<<"__________________________________________________"<<endl;
				cout<<"Copiando..."<<endl;
				delete(imagem1);
				break;
			case '2':
				cout<<"Tipo da Imagem (1)PGM ou (2)PPM? ";
				cin>>tipo;
				cin.ignore(256, '\n');//ignora ate 256 caracteres invalidos
				fflush(stdin);
				switch(tipo){
					case '1':
						pgm1 = new PGM();
						cout<<"Digite o caminho da Imagem PGM: ";
						getline(cin,path_in);
						cout<<"__________________________________________________"<<endl;
						try{
							pgm1->lerImagem(path_in);
							cout<<"Mensagem: "<<pgm1->decifraMensagem()<<endl;
						}
						catch(int erro){
							tipo_erro(erro);
						}
						delete(pgm1);
						break;
					case '2':
						
						ppm1 = new PPM();
						cout<<"Digite o caminho da Imagem PPM: ";
						getline(cin,path_in);
						cout<<"__________________________________________________"<<endl;
						try{
							ppm1->lerImagem(path_in);
							cout<<"Mensagem: "<<ppm1->decifraMensagem()<<endl;
						}
						catch(int erro){
							tipo_erro(erro);
						}
						delete(ppm1);
						break;
					default:
						cout<<"__________________________________________________"<<endl;
						cout<<"Opcao Invalida"<<endl;

						break;
						
				}
				
				break;
			case '0':
				cout<<"__________________________________________________"<<endl;
				continue;
				break;
			default:
				cout<<"__________________________________________________"<<endl;
				cout<<"Opcao Invalida"<<endl;
				break;
		}
		cout<<"__________________________________________________"<<endl;
		cin.get(); //usando para pausar execução na mensagem
		system("clear");
	}
	return 0;
}

void tipo_erro(int erro){
	switch (erro){
		case -1:
			cout<<"Falha ao ler arquivo"<<endl;
			break;
		case -2:
			cout<<"Falha ao gravar arquivo"<<endl;
			break;
		case -3:
			cout<<"Imagem nao e do tipo selecionado"<<endl;
			break;
		case -4:
			cout<<"Imagem invalida"<<endl;
			break;
		case -5:
			cout<<"Parametros insuficientes para decodificacao"<<endl;
			break;
		default:
			cout<<"Erro indefinido"<<endl;
	}
}
