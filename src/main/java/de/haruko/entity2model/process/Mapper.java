/**
* $HeadURL$
* $LastChangedRevision$
* $LastChangedDate$
* $Author$
* Created on 30.01.2018, 20:12:58
* ===========================================================================
* Copyright (c) 2006-2018 OrgaCard Siemantel & Alt GmbH. All rights reserved.
*/

package de.haruko.entity2model.process;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import com.googlecode.jmapper.api.MappedClass;
import de.haruko.entity2model.entity.Detail;
import de.haruko.entity2model.entity.Master;
import de.haruko.entity2model.entity.Referenz;
import de.haruko.entity2model.target.DetailModel;
import de.haruko.entity2model.target.MasterModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;


public class Mapper {
    private Master master;
    public static void main(String [] args) {
        Mapper m = new Mapper();
                        
        
        JMapperAPI api = new JMapperAPI();
        MappedClass mappedClass = new MappedClass(DetailModel.class);
        api.add(mappedClass);
        JMapper<MasterModel,Master> jmapper = new JMapper<MasterModel,Master>(MasterModel.class, Master.class, api);
        MasterModel masterModel = (MasterModel) jmapper.getDestination(m.buildMaster());
        int i = 0;
    }
    
    
    
    private Master buildMaster() {
        master = new Master("1", new Date(), new BigDecimal(15.3d), "admin", new Date(), new ArrayList<>());
        master.getDetails().add(new Detail("d1", new Referenz("r1", "ref1"), "adminDetail", new Date()));
        master.getDetails().add(new Detail("d2", new Referenz("r2", "ref2"), "adminDetail", new Date()));
        master.getDetails().add(new Detail("d3", new Referenz("r3", "ref3"), "adminDetail", new Date()));
        return master;
    }
}
