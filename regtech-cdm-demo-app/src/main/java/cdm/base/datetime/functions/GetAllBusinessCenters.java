package cdm.base.datetime.functions;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.metafields.FieldWithMetaBusinessCenterEnum;
import cdm.base.datetime.metafields.ReferenceWithMetaBusinessCenters;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(GetAllBusinessCenters.GetAllBusinessCentersDefault.class)
public abstract class GetAllBusinessCenters implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected cdm.base.datetime.functions.GetAllBusinessCenters getAllBusinessCenters;

	/**
	* @param businessCenters 
	* @return businessCenterEnums 
	*/
	public List<BusinessCenterEnum> evaluate(BusinessCenters businessCenters) {
		List<BusinessCenterEnum> businessCenterEnums = doEvaluate(businessCenters);
		
		return businessCenterEnums;
	}

	protected abstract List<BusinessCenterEnum> doEvaluate(BusinessCenters businessCenters);

	public static class GetAllBusinessCentersDefault extends GetAllBusinessCenters {
		@Override
		protected List<BusinessCenterEnum> doEvaluate(BusinessCenters businessCenters) {
			List<BusinessCenterEnum> businessCenterEnums = new ArrayList<>();
			return assignOutput(businessCenterEnums, businessCenters);
		}
		
		protected List<BusinessCenterEnum> assignOutput(List<BusinessCenterEnum> businessCenterEnums, BusinessCenters businessCenters) {
			businessCenterEnums.addAll(MapperS.of(businessCenters).<FieldWithMetaBusinessCenterEnum>mapC("getBusinessCenter", _businessCenters -> _businessCenters.getBusinessCenter()).<BusinessCenterEnum>map("getValue", _f->_f.getValue()).getMulti());
			
			if (exists(MapperS.of(businessCenters).<ReferenceWithMetaBusinessCenters>map("getBusinessCentersReference", _businessCenters -> _businessCenters.getBusinessCentersReference()).<BusinessCenters>map("getValue", _f->_f.getValue())).getOrDefault(false)) {
				businessCenterEnums.addAll(getAllBusinessCenters.evaluate(MapperS.of(businessCenters).<ReferenceWithMetaBusinessCenters>map("getBusinessCentersReference", _businessCenters -> _businessCenters.getBusinessCentersReference()).<BusinessCenters>map("getValue", _f->_f.getValue()).get()));
			} else {
				businessCenterEnums.addAll(Collections.<BusinessCenterEnum>emptyList());
			}
			
			businessCenterEnums = new ArrayList<>(distinct(MapperC.<BusinessCenterEnum>of(businessCenterEnums)).getMulti());
			
			return businessCenterEnums;
		}
	}
}
