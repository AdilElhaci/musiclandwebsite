
package converter;

import dao.CategoryDao;
import entity.Category;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter (value="categoryConverter")
public class CategoryConverter implements Converter{

    private CategoryDao categoryDao;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getCategoryDao().findCategory(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Category category = (Category) arg2;
        return category.getId().toString();
    }

    public CategoryDao getCategoryDao() {
        if(this.categoryDao == null)
            this.categoryDao = new CategoryDao();
        return categoryDao;
    }

    
    
}
