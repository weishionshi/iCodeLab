package com.a3.ast;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.integration.test.action.template.OpenActionTemplate;
public class BlockVistor extends ASTVisitor {
    private static final Logger log = LoggerFactory.getLogger(BlockVistor.class);
    private List<String> hrList = new ArrayList<String>();
    
    @Override
    public boolean visit(MethodInvocation methodInvocation){
        IMethodBinding binding =methodInvocation.resolveMethodBinding();
        if(binding.getClass().equals(OpenActionTemplate.class)&&binding.getName().equals("setContext")){
            String httpRequest = methodInvocation.getExpression();
            if(!this.hrList.contains(httpRequest)) {
                this.hrList.add(httpRequest);
                log.debug("this.hrList:"+this.hrList.toString());
            }
            
        }
        
        return true;
    }
    
    //getter and setter
    public List<String> getHrList() {
        return hrList;
    }
    public void setHrList(List<String> hrList) {
        this.hrList = hrList;
    }
}