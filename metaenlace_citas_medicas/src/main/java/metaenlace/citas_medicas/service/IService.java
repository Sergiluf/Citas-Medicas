package metaenlace.citas_medicas.service;

import java.util.List;

public interface IService<T> {
	public List<T> findAll();
	public T findById(Integer id);
	public T save(T dto);
	public boolean delete(Integer id);
}
