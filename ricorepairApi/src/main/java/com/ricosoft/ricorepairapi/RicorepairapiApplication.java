package com.ricosoft.ricorepairapi;

import com.ricosoft.ricorepairapi.entity.*;
import com.ricosoft.ricorepairapi.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class RicorepairapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RicorepairapiApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(UsuarioRepository usuarioRepository, ParteRepository parteRepository, IntervencionRepository intervencionRepository) {
//		return args -> {
//			// Cargar usuarios
//			List<Usuario> usuarios = Arrays.asList(
//				new Usuario("Lucía","Rico","lucia@gmail.com", "987654321",
//						"Lucia.R7", Rol.ADMIN),
//				new Usuario("Alfredo","Rico","alfredo@gmail.com", "444987654",
//						"Alfre.R8", Rol.ADMIN),
//				new Usuario("Ana","Pérez","ana.perez@gmail.com", "123456789",
//						"password123", Rol.USER),
//				new Usuario("María","González","maria.gonzalez@gmail.com", "555123456",
//						"password789", Rol.USER),
//				new Usuario("Carlos","Fernández","carlos.fernandez@gmail.com", "333654321",
//						"password202", Rol.USER)
//			);
//			usuarioRepository.saveAll(usuarios);
//
//			// Cargar partes
//			List<Parte> partes = Arrays.asList(
//					new Parte(1, LocalDateTime.now(), "Ordenador de escritorio", "Teclado y ratón", Estado.PE, new Date(),
//							"Problemas de encendido", "Se realizó una revisión completa del hardware. Se cambió la fuente de alimentación y se limpiaron los componentes internos. El equipo ahora arranca sin problemas.", DocumentacionTecnica.SI),
//					new Parte(1, LocalDateTime.now(), "Portátil HP", "Cargador", Estado.EP, new Date(),
//							"Pantalla parpadeante", "Se diagnosticó un fallo en el cable flex de la pantalla. Se reemplazó el cable y se verificó el correcto funcionamiento del equipo.", DocumentacionTecnica.NO),
//					new Parte(2, LocalDateTime.now(), "Tablet Samsung Galaxy", "Funda protectora", Estado.EC, new Date(),
//							"Batería no carga", "Se reemplazó la batería y se revisó el puerto de carga. La tablet ahora carga correctamente y la batería tiene el rendimiento esperado.", DocumentacionTecnica.SI),
//					new Parte(2, LocalDateTime.now(), "Portátil Dell", "Cargador y ratón", Estado.PE, new Date(),
//							"Lentitud general", "Se realizó una limpieza de software y se aumentó la memoria RAM. El equipo ahora tiene un rendimiento significativamente mejor.", DocumentacionTecnica.NO),
//					new Parte(3, LocalDateTime.now(), "Ordenador de escritorio", "Teclado mecánico", Estado.EP, new Date(),
//							"Problemas con el sistema operativo", "Se reinstaló el sistema operativo y se configuraron las aplicaciones necesarias. El equipo ahora funciona sin problemas de software.", DocumentacionTecnica.SI),
//					new Parte(3, LocalDateTime.now(), "Tablet iPad", "Apple Pencil", Estado.EC, new Date(),
//							"Pantalla rota", "Se reemplazó la pantalla y se verificó que no hubiera daños adicionales. La funcionalidad táctil y visual quedó completamente restaurada.", DocumentacionTecnica.NO),
//					new Parte(4, LocalDateTime.now(), "Portátil Lenovo", "Cargador y funda", Estado.PE, new Date(),
//							"Teclado no responde", "Se identificó un daño en el teclado debido a líquidos derramados. Se reemplazó el teclado completo, y se verificó el correcto funcionamiento del equipo.", DocumentacionTecnica.SI),
//					new Parte(4, LocalDateTime.now(), "Ordenador de escritorio", "Monitor y teclado", Estado.EC, new Date(),
//							"Fallo en la tarjeta gráfica", "Se reemplazó la tarjeta gráfica y se actualizó el software de drivers. Ahora el equipo puede manejar aplicaciones gráficas de alto rendimiento sin problemas.", DocumentacionTecnica.NO),
//					new Parte(5, LocalDateTime.now(), "Tablet Huawei", "Cargador", Estado.EP, new Date(),
//							"Problemas de conectividad Wi-Fi", "Se realizó un análisis de la conectividad, y se reemplazó el módulo Wi-Fi. El dispositivo ahora se conecta de forma estable a las redes inalámbricas.", DocumentacionTecnica.SI),
//					new Parte(5, LocalDateTime.now(), "Portátil ASUS", "Ratón inalámbrico", Estado.EC, new Date(),
//							"Sobrecalentamiento", "Se limpió el sistema de refrigeración y se aplicó pasta térmica nueva en el procesador. El portátil ahora mantiene temperaturas estables durante el uso prolongado.", DocumentacionTecnica.NO)
//			);
//			parteRepository.saveAll(partes);
//
//
//			// Cargar intervenciones
//			List<Intervencion> intervenciones = Arrays.asList(
//					new Intervencion(1, new Date(), "Lucía Rico", "Reemplazo de fuente de alimentación", "Se sustituyó la fuente de alimentación defectuosa por una nueva."),
//					new Intervencion(1, new Date(), "Lucía Rico", "Reemplazo de pantalla", "Se cambió la pantalla por una nueva debido a daños."),
//					new Intervencion(1, new Date(), "Lucía Rico", "Limpieza de hardware", "Se realizó una limpieza exhaustiva del interior del portátil."),
//					new Intervencion(2, new Date(), "Lucía Rico", "Actualización de RAM", "Se aumentó la memoria RAM a 16 GB para mejorar el rendimiento."),
//					new Intervencion(2, new Date(), "Lucía Rico", "Reinstalación del sistema operativo", "Se reinstaló Windows y se configuraron las aplicaciones necesarias."),
//					new Intervencion(2, new Date(), "Lucía Rico", "Reemplazo de teclado", "Se cambió el teclado por daños por líquidos."),
//					new Intervencion(2, new Date(), "Lucía Rico", "Reemplazo de batería", "Se reemplazó la batería por una nueva de mayor capacidad."),
//					new Intervencion(3, new Date(), "Lucía Rico", "Revisión de conectividad Wi-Fi", "Se revisó la tarjeta de red y se ajustaron las configuraciones."),
//					new Intervencion(3, new Date(), "Lucía Rico", "Reemplazo de tarjeta gráfica", "Se cambió la tarjeta gráfica para solucionar problemas de rendimiento."),
//					new Intervencion(3, new Date(), "Lucía Rico", "Instalación de software", "Se instaló el software antivirus y se configuraron las actualizaciones automáticas.")
//			);
//			intervencionRepository.saveAll(intervenciones);
//		};
//
//	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
