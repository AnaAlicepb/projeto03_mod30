package domain;

import annotation.ColunaTabela;
import annotation.Tabela;
import annotation.TipoChave;
import dao.Persistente;

@Tabela("tb_cliente")
public class Cliente implements Persistente {

    @ColunaTabela(dbName = "id", setJavaName = "setId")
    private Long idCliente;

    @ColunaTabela(dbName = "nome", setJavaName = "setNome")
    private String nome;

    @TipoChave("getCpf")
    @ColunaTabela(dbName = "cpf", setJavaName = "setCpf")
    private Long cpf;

    @ColunaTabela(dbName = "tel", setJavaName = "setTelefone")
    private Long telefone;

    @ColunaTabela(dbName = "endereco", setJavaName = "setEndereco")
    private String endereco;

    @ColunaTabela(dbName = "numero", setJavaName = "setNumero")
    private Integer numero;

    @ColunaTabela(dbName = "cidade", setJavaName = "setCidade")
    private String cidade;

    @ColunaTabela(dbName = "estado", setJavaName = "setEstado")
    private String estado;

    @ColunaTabela(dbName = "idade", setJavaName = "setIdade")
    private Long idade;

    @ColunaTabela(dbName = "email", setJavaName = "setEmail")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Herdado da interface Persistente
    public Long getId() {
        return idCliente;
    }

    // Herdado da interface Persistente
    public void setId(Long id) {
        this.idCliente = id;
    }
}
