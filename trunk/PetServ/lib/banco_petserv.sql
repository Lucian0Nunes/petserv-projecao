DROP TABLE produto;
CREATE TABLE produto (id_produto int(2) NOT NULL AUTO_INCREMENT, ds_descricao varchar(100) NOT NULL, dt_cadastro date NOT NULL, qt_qtdestoque int(2) NOT NULL, vr_venda decimal, vr_compra decimal, PRIMARY KEY (id_produto)) ENGINE=MyISAM DEFAULT CHARSET=latin1;
DROP TABLE servico;
CREATE TABLE servico (id_servico int(2) NOT NULL AUTO_INCREMENT, ds_descricao varchar(100) NOT NULL, dt_cadastro date NOT NULL, tempo_medio float NOT NULL, vr_venda decimal NOT NULL, bol_disponibilidade tinyint(1), PRIMARY KEY (id_servico)) ENGINE=MyISAM DEFAULT CHARSET=latin1;
