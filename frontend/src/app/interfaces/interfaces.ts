export interface Paginacao<T> {
  content: Array<T>;
  last: boolean;
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
  first: boolean;
  numberOfElements: number;
  empty: number;
}

export interface DispositivoDto {
  id: string;
  nome: string;
  criadoEm: string;
  topico: string;
  ultimaLocalizacao?: string;
  ultimaAtualizacao?: string;
}

export interface DispositivoSimpleDto {
  id: string;
  nome: string;
}

export interface LocalizacaoDto {
  id: string;
  latitude: number;
  longitude: number;
  bateriaStatus: string;
  bateria: number;
  gatilho: string;
  tipoConexao: string;
  horarioGps: string;
  velocidade: number;
  dispositivo: DispositivoSimpleDto;
  pressaoBarometrica: number;
  endereco: string;
}
