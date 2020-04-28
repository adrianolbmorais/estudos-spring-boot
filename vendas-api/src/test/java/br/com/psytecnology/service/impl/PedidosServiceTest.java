package br.com.psytecnology.service.impl;

import br.com.psytecnology.domain.entity.*;

import br.com.psytecnology.domain.enums.StatusPedido;
import br.com.psytecnology.domain.repository.ClientesRepository;
import br.com.psytecnology.domain.repository.ItemPedidoRepository;
import br.com.psytecnology.domain.repository.PedidosRepository;
import br.com.psytecnology.domain.repository.ProdutosRepository;
import br.com.psytecnology.domain.entity.dto.MapperDtoToEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;

@RunWith(Parameterized.class)
public class PedidosServiceTest {

    @Mock
    private PedidosRepository pedidosRepositoryMock;

    @Mock
    private ClientesRepository clientesRepositoryMock;

    @Mock
    private ProdutosRepository produtosRepositoryMock;

    @Mock
    private ItemPedidoRepository itemPedidoRepositoryMock;

    @Mock
    private MapperDtoToEntity mapperDtoToEntityMock;

    @InjectMocks
    private PedidosServiceImpl pedidosServiceImplMock;

    @Parameter
    public Integer idPedido;

    @Parameter(value = 1)
    public Pedido pedidoBuilderAtual;

    @Parameter(value = 2)
    public Pedido pedidoBuilderAtualizado;

    @Parameter(value = 3)
    public String descricaoCenario;

    private static Integer idPedido1 = 1;
    private static Integer idPedido2 = 2;

    private static Pedido pedidoBuilderRealizado1 = PedidoBuilder.criaUmPedido().comId(1).comDataPedido(LocalDate.now()).comCliente(new Cliente()).comStatus(StatusPedido.REALIZADO).agora();
    private static Pedido pedidoBuilderCancelado1 = PedidoBuilder.criaUmPedido().comId(1).comDataPedido(LocalDate.now()).comCliente(new Cliente()).comStatus(StatusPedido.CANCELADO).agora();

    @Parameters(name = "{3}")
    public static Collection<Object[]> getParameter(){
        return Arrays.asList(new Object[][]{
                {idPedido1, pedidoBuilderRealizado1, pedidoBuilderCancelado1, "Cenario com Sucesso."}
        });
    }

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findPedidoByIdTest() {
        //Cenário
        Mockito.when(pedidosRepositoryMock.findById(idPedido)).thenReturn(Optional.ofNullable(pedidoBuilderAtual));
        //Ação
        Optional<Pedido> pedidoResult = pedidosServiceImplMock.findPedidoById(idPedido);
        //Verificação

        Mockito.verify(pedidosRepositoryMock, Mockito.times(1)).findById(idPedido);
        errorCollector.checkThat("O objeto está nulo", pedidoResult, is(not(equalTo(nullValue()))));
        if(idPedido == 1) {
            errorCollector.checkThat("Os ids dos objetos divergem", pedidoResult.get().getId(), is(equalTo(idPedido)));
        }else {
            errorCollector.checkThat("Os ids dos objetos divergem", pedidoResult.get().getId(), is(not(equalTo(idPedido))));
        }
    }

    @Test
    public void atualizarStatus() {
        //Cenário
        Mockito.when(pedidosRepositoryMock.findById(idPedido)).thenReturn(Optional.ofNullable(pedidoBuilderAtual));
        Mockito.when(pedidosRepositoryMock.save(pedidoBuilderAtualizado)).thenReturn(pedidoBuilderAtualizado);
        //Ação
        Pedido pedidoResult = pedidosServiceImplMock.atualizarStatus(idPedido, pedidoBuilderAtualizado.getStatus());
        //Verificação
        Mockito.verify(pedidosRepositoryMock, Mockito.times(1)).findById(idPedido);
        errorCollector.checkThat("O objeto está nulo", pedidoResult, is(not(equalTo(nullValue()))));
        errorCollector.checkThat("Os ids dos objetos divergem", pedidoResult.getId(), is(equalTo(pedidoBuilderAtual.getId())));
        errorCollector.checkThat("O Status dos Pedidos são guais", pedidoResult.getStatus(), is(equalTo(pedidoBuilderAtual.getStatus())));
    }

}
