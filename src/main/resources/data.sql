-- DELETE
DELETE FROM public.tb_consulta;
DELETE FROM public.tb_profissional_convenio;
DELETE FROM public.tb_paciente;
DELETE FROM public.tb_profissional;
DELETE FROM public.tb_convenio;

-- INSERT - USUÁRIOS
INSERT INTO public.tb_usuario(
	id, date_created, password, username)
	VALUES (1, now()::timestamp(0), '$2a$10$lhlvPQC9umqBEQwnu7mxBu58S1P6Ya3jaVHMsvUXygCzgKut9oqbq', 'MARIA');

INSERT INTO public.tb_usuario(
	id, date_created, password, username)
	VALUES (2, now()::timestamp(0), '$2a$10$lhlvPQC9umqBEQwnu7mxBu58S1P6Ya3jaVHMsvUXygCzgKut9oqbq', 'JOSE');
	
INSERT INTO public.tb_usuario(
	id, date_created, password, username)
	VALUES (3, now()::timestamp(0), '$2a$10$lhlvPQC9umqBEQwnu7mxBu58S1P6Ya3jaVHMsvUXygCzgKut9oqbq', 'JOAO');

INSERT INTO public.tb_usuario(
	id, date_created, password, username)
	VALUES (4, now()::timestamp(0), '$2a$10$lhlvPQC9umqBEQwnu7mxBu58S1P6Ya3jaVHMsvUXygCzgKut9oqbq', 'ANTONIO');

INSERT INTO public.tb_usuario(
	id, date_created, password, username)
	VALUES (5, now()::timestamp(0), '$2a$10$lhlvPQC9umqBEQwnu7mxBu58S1P6Ya3jaVHMsvUXygCzgKut9oqbq', 'PEDRO');

INSERT INTO public.tb_usuario(
	id, date_created, password, username)
	VALUES (6, now()::timestamp(0), '$2a$10$lhlvPQC9umqBEQwnu7mxBu58S1P6Ya3jaVHMsvUXygCzgKut9oqbq', 'FELIPE');

INSERT INTO public.tb_usuario(
	id, date_created, password, username)
	VALUES (7, now()::timestamp(0), '$2a$10$lhlvPQC9umqBEQwnu7mxBu58S1P6Ya3jaVHMsvUXygCzgKut9oqbq', 'CARLOS');

INSERT INTO public.tb_usuario(
	id, date_created, password, username)
	VALUES (8, now()::timestamp(0), '$2a$10$lhlvPQC9umqBEQwnu7mxBu58S1P6Ya3jaVHMsvUXygCzgKut9oqbq', 'TIAGO');

-- INSERT - PACIENTES
INSERT INTO public.tb_paciente(
	id, usuario_id, total_appointment, status, date_created)
	VALUES (1, 1, '1', 'ONLINE', now()::timestamp(0));

INSERT INTO public.tb_paciente(
	id, usuario_id, total_appointment, status, date_created)
	VALUES (2, 2, '1', 'ONLINE', now()::timestamp(0));

INSERT INTO public.tb_paciente(
	id, usuario_id, total_appointment, status, date_created)
	VALUES (3, 3, '1', 'OFFLINE', now()::timestamp(0));

INSERT INTO public.tb_paciente(
	id, usuario_id, total_appointment, status, date_created)
	VALUES (4, 4, '1', 'OFFLINE', now()::timestamp(0));

INSERT INTO public.tb_paciente(
	id, usuario_id, total_appointment, status, date_created)
	VALUES (5, 5, '1', 'OFFLINE', now()::timestamp(0));

-- INSERT - PROFISSIONAIS
INSERT INTO public.tb_profissional(
	id, usuario_id, date_created, name, status, crm)
	VALUES (1, 6, now()::timestamp(0), 'FELIPE', 'ONLINE', '12345');

INSERT INTO public.tb_profissional(
	id, usuario_id, date_created, name, status, crm)
	VALUES (2, 7, now()::timestamp(0), 'CARLOS', 'ONLINE', '12345');

INSERT INTO public.tb_profissional(
	id, usuario_id, date_created, name, status, crm)
	VALUES (3, 8, now()::timestamp(0), 'TIAGO', 'OFFLINE', '12345');

-- INSERT - CONVÊNIOS
INSERT INTO public.tb_convenio(
	id, date_created, name, cnpj, price)
	VALUES (1, now()::timestamp(0), 'PLANO SAÚDE I', '12345', 180.0);

INSERT INTO public.tb_convenio(
	id, date_created, name, cnpj, price)
	VALUES (2, now()::timestamp(0), 'PLANO SAÚDE II', '12345', 240.0);

-- INSERT - PROFISSIONAIS CONVÊNIOS
INSERT INTO public.tb_profissional_convenio(
	id, date_created, profissional_id, convenio_id, fl_ativo)
	VALUES (1, now()::timestamp(0), 1, 1, TRUE);

INSERT INTO public.tb_profissional_convenio(
	id, date_created, profissional_id, convenio_id, fl_ativo)
	VALUES (2, now()::timestamp(0), 1, 2, TRUE);

INSERT INTO public.tb_profissional_convenio(
	id, date_created, profissional_id, convenio_id, fl_ativo)
	VALUES (3, now()::timestamp(0), 2, 1, TRUE);

INSERT INTO public.tb_profissional_convenio(
	id, date_created, profissional_id, convenio_id, fl_ativo)
	VALUES (4, now()::timestamp(0), 2, 2, TRUE);

INSERT INTO public.tb_profissional_convenio(
	id, date_created, profissional_id, convenio_id, fl_ativo)
	VALUES (5, now()::timestamp(0), 3, 1, TRUE);

-- INSERT - CONSULTAS
INSERT INTO public.tb_consulta(
	id, date_created, paciente_id, profissional_id, convenio_id, dt_consulta, status)
	VALUES (nextval('public."sq_consulta"'), now()::timestamp(0), 1, 1, 1, to_date('14/08/2022 00:14', 'DD/MM/YYYY HH24:MI'), 'MARCADA');

INSERT INTO public.tb_consulta(
	id, date_created, paciente_id, profissional_id, convenio_id, dt_consulta, status)
	VALUES (nextval('public."sq_consulta"'), now()::timestamp(0), 2, 2, 2, to_date('14/08/2022 00:14', 'DD/MM/YYYY HH24:MI'), 'MARCADA');

INSERT INTO public.tb_consulta(
	id, date_created, paciente_id, profissional_id, convenio_id, dt_consulta, status)
	VALUES (nextval('public."sq_consulta"'), now()::timestamp(0), 3, 3, 1, to_date('14/08/2022 00:14', 'DD/MM/YYYY HH24:MI'), 'MARCADA');

INSERT INTO public.tb_consulta(
	id, date_created, paciente_id, profissional_id, convenio_id, dt_consulta, status)
	VALUES (nextval('public."sq_consulta"'), now()::timestamp(0), 1, 2, 1, to_date('14/08/2022 00:14', 'DD/MM/YYYY HH24:MI'), 'CANCELADA');

INSERT INTO public.tb_consulta(
	id, date_created, paciente_id, profissional_id, convenio_id, dt_consulta, status)
	VALUES (nextval('public."sq_consulta"'), now()::timestamp(0), 2, 1, 1, to_date('14/08/2022 00:14', 'DD/MM/YYYY HH24:MI'), 'EFETIVADA');

