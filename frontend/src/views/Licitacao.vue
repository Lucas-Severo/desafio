<template>
    <div class="container">
        <div class="options">
            <input checked type="radio" id="all" name="options" @click="selectOption(1)">
            <label class="option" for="all">Mostrar</label>
            <input type="radio" id="price" name="options" @click="selectOption(2)">  
            <label class="option" for="price">Cadastrar</label>  
        </div>

        <ul class="licitacoes" v-show="selected == 1">
            <li class="licitacao" v-for="licitacao in licitacoes" :key="licitacao.id">
                <div class="licitacao__info">
                    <div class="licitacao__descricao">
                        Descrição: {{licitacao.descricao}}
                    </div>
                    <div class="licitacao__classificacao">
                        Classificação: {{ licitacao.classificacao}}
                    </div>
                </div>
                <div class="licitacao__options">
                    <p class="licitacao__delete" @click="handleDelete(licitacao.id)">Excluir</p>
                    <p class="licitacao__update">Atualizar</p>
                    <p class="licitacao__propostas" @click="showPropostas(licitacao.id, licitacao.classificacao)">Propostas</p>
                </div>
            </li>
        </ul>

        <div class="form" v-show="selected == 2">
            <form action="" methdo="post" @submit.prevent="handleSubmit">
                <h1 class="form__title">Cadastrar Licitação</h1>
                <label class="form__text" for="desc">Descrição</label>
                <input type="text" value="licitacaoDescricao" v-model="licitacaoDescricao" required>
                <label class="form__text" for="classificacao">Classificação</label>
                <select v-model="classificacao" required name="classificacao" id="classificacao">
                    <option value="MENOR_PRECO">Menor Preço</option>
                    <option value="NOTA_PRECO">Nota Preço</option>
                </select>
                <button type="input" class="form__button">Cadastrar</button>
            </form>
        </div>
    </div>
</template>

<script>
import api from '../services/api';
import router from '../router';

export default {
    data: () => ({
        licitacoes: [],
        licitacaoDescricao: "",
        classificacao: null,
        selected: 1
    }),
    methods: {
        selectOption(option) {
            this.selected = option;
        },

        async handleDelete(id) {
            api.delete(`/licitacao/${id}`);
            this.licitacoes = this.licitacoes.filter(proposta => {
                return proposta.id != id;
            });
        },

        handleSubmit() {
            api.post('/licitacao', {
                "descricao": this.licitacaoDescricao,
                "classificacao": this.classificacao
            })
            .then(response => {
                this.licitacoes = [...this.licitacoes, response.data]
            });

            this.selected = 1;
        },

        showPropostas(licitacaoId, licitacaoClassificacao) {
            router.push({ name: 'propostas', params: {licitacaoId, licitacaoClassificacao }});
        }
    },
    mounted() {
        api.get('/licitacao')
        .then(response => {
            return response.data;
        })
        .then(response => {this.licitacoes = response})
    }
}
</script>

<style scoped>
    .container {
        padding: 1em;
    }
    .options {
        display: flex;
        justify-content: center;
    }
    label.option {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100px;
        height: 40px;
        background: #fff;
        box-shadow: 0px 3px 4px rgba(0, 0, 0, 0.5);
        cursor: pointer;
        border-radius: 4px;
    }
    input[name="options"] {
        visibility: hidden;
    }
    input[name="options"]:checked + label{
        background: #0a0;
        font-weight: bold;
        color: #FFF;
    }

    .licitacoes {
        padding-top: 1em;
        list-style: none;
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 20px;
        width: 80%;
        margin: 0 auto;
    }

    .licitacao {
       border: 1px solid #ccc;
       padding: 2em; 
       background-color: #fff;
       border-radius: 4px;
       font-size: 1.2em;
       position: relative;
       display: flex;
       flex-direction: column;
    }

    .licitacao__info {
        flex: 1;
    }

    .licitacao__options {
        display: flex;
        padding-top: 20px;
    }

    .licitacao__delete,
    .licitacao__update,
    .licitacao__propostas {
        color: rgba(0, 144, 144, 0.8);
        cursor: pointer;
        margin-right: 10px;
        font-size: 0.9em;
        line-height: 0.9em;
        font-weight: bold;
        text-transform: uppercase;
    }

    .form {
        padding-top: 20px;
        display: flex;
        justify-content: center;
        text-align: center;
    }

    .form form {
        display: flex;
        flex-direction: column;
        text-align: left;
    }

    .form__text,
    .form__button {
        margin-top: 1em;
    }

    input,
    select,
    button {
        padding: 0.8em;
    }

    button {
        border-radius: 4px;
        border: none;
        cursor: pointer;
        background: #00AA00DD;
        color: #FFF;
        font-weight: bold;
        font-size: 1em;
    }
</style>