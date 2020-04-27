package br.com.psytecnology;

//import br.com.psytecnology.domain.entity.Cliente;
//import br.com.psytecnology.domain.entity.Pedido;
//import br.com.psytecnology.domain.repository.ClienteRepository;
//import br.com.psytecnology.domain.repository.PedidoRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//@SpringBootApplication
//public class OldVendasApplication {
//
//    private ClienteRepository clienteRepository;
//
//    private PedidoRepository pedidoRepository;
//
//    public OldVendasApplication(ClienteRepository clienteRepository, PedidoRepository pedidoRepository){
//        this.clienteRepository = clienteRepository;
//        this.pedidoRepository = pedidoRepository;
//    }
//
//    @Bean
//    public CommandLineRunner init(){
//        return args -> {
//            Cliente cliente1 = new Cliente();
//            cliente1.setNome("Adriano Morais");
//            clienteRepository.save(cliente1);
//
//            Pedido pedido1 = new Pedido();
//            pedido1.setCliente(cliente1);
//            pedido1.setDataPedido(LocalDate.now());
//            pedido1.setValorTotal(BigDecimal.valueOf(100));
//
//            pedidoRepository.save(pedido1);
//
//            Cliente clienteResult = clienteRepository.findClienteFetchPedidos(cliente1.getId());
//            System.out.println(clienteResult);
//            System.out.println(clienteResult.getPedidos());
//
//            pedidoRepository.findByCliente(cliente1).forEach(System.out::println);
//
//            Cliente cliente2 = new Cliente();
//            cliente2.setNome("André Morais");
//
//            Cliente cliente3 = new Cliente();
//            cliente3.setNome("Outro Cliente");
//
//            System.out.println("Salvando o  cliente1");
//            System.out.println(jpaRepositoriesClienteRepository.save(cliente1));
//            System.out.println("Salvando o  cliente2");
//            System.out.println(jpaRepositoriesClienteRepository.save(cliente2));
//            System.out.println("Salvando o  cliente3");
//            System.out.println(jpaRepositoriesClienteRepository.save(cliente3));
//
//            System.out.println("Existe cliente com o nome: " + cliente1.getNome());
//            System.out.println(jpaRepositoriesClienteRepository.existsByNome(cliente1.getNome()));
//
//            System.out.println("Recuperando todos os cliente");
//            List<Cliente> clientes = jpaRepositoriesClienteRepository.findAll();
//            clientes.forEach(System.out::println);
//
//            System.out.println("Atualizando todos os clientes");
//            clientes.forEach(cliente -> {
//                cliente.setNome(cliente.getNome() + " Atualizado");
//                System.out.println(jpaRepositoriesClienteRepository.save(cliente));
//            });
//
//            System.out.println("Recuperando todos os clientes");
//            clientes = jpaRepositoriesClienteRepository.findAll();
//            clientes.forEach(System.out::println);
//
//            System.out.println("Buscando cliente por um nome especifico");
//            Cliente clientePorUmNome = jpaRepositoriesClienteRepository.findOneByNome(cliente1.getNome() + " Atualizado");
//            System.out.println(clientePorUmNome);
//
//            System.out.println("Buscando cliente por nome like QueryMethod");
//            List<Cliente> clienteNome = jpaRepositoriesClienteRepository.findByNomeLike("%" + cliente1.getNome() + "%");
//            clienteNome.forEach(System.out::println);
//
//            System.out.println("Buscando cliente por nome like Query");
//            List<Cliente> clienteNomeQueryMethod = jpaRepositoriesClienteRepository.buscarPorNomeQuery(cliente1.getNome());
//            clienteNomeQueryMethod.forEach(System.out::println);
//
//            System.out.println("Buscando cliente por nome like NativeQuery");
//            List<Cliente> clienteNomeNativeQuery = jpaRepositoriesClienteRepository.buscarPorNomeNativQuery(cliente1.getNome());
//            clienteNomeNativeQuery.forEach(System.out::println);
//
//            System.out.println("Recuperando todos os clientes");
//            clientes = jpaRepositoriesClienteRepository.findAll();
//            clientes.forEach(System.out::println);
//
//            cliente1 = jpaRepositoriesClienteRepository.findById(cliente1.getId()).orElseThrow();
//            cliente2 = jpaRepositoriesClienteRepository.findById(cliente2.getId()).orElseThrow();
//            cliente3 = jpaRepositoriesClienteRepository.findById(cliente3.getId()).orElseThrow();
//
//            System.out.println("Deletando Cliente1");
//            jpaRepositoriesClienteRepository.delete(cliente1);
//
//            System.out.println("Deletando Cliente2");
//            jpaRepositoriesClienteRepository.deleteByNome(cliente2.getNome());
//
//            System.out.println("Deletando Cliente3");
//            jpaRepositoriesClienteRepository.deleteByNomeQuery(cliente3.getNome());
//
//            System.out.println("Deletando todos os clientes");
//            jpaRepositoriesClienteRepository.findAll().forEach(cliente -> {
//                jpaRepositoriesClienteRepository.delete(cliente);
//                System.out.println("Cliente " + cliente.getId() + " deletado!");
//            });
//
//            System.out.println("Recuperando todos os clientes");
//            clientes = jpaRepositoriesClienteRepository.findAll();
//            if(clientes.isEmpty()){
//                System.out.println("Nenhum cliente encontrado!");
//            }else{
//                clientes.forEach(System.out::println);
//            }
//        };
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(OldVendasApplication.class, args);
//    }
//}