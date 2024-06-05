package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.CommodityReferenceFramework;
import cdm.base.staticdata.asset.common.validation.CommodityReferenceFrameworkTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.CommodityReferenceFrameworkValidator;
import cdm.base.staticdata.asset.common.validation.exists.CommodityReferenceFrameworkOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=CommodityReferenceFramework.class)
public class CommodityReferenceFrameworkMeta implements RosettaMetaData<CommodityReferenceFramework> {

	@Override
	public List<Validator<? super CommodityReferenceFramework>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.CommodityReferenceFrameworkCommodityReferenceFrameworkChoice.class)
		);
	}
	
	@Override
	public List<Function<? super CommodityReferenceFramework, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CommodityReferenceFramework> validator() {
		return new CommodityReferenceFrameworkValidator();
	}

	@Override
	public Validator<? super CommodityReferenceFramework> typeFormatValidator() {
		return new CommodityReferenceFrameworkTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CommodityReferenceFramework, Set<String>> onlyExistsValidator() {
		return new CommodityReferenceFrameworkOnlyExistsValidator();
	}
}
