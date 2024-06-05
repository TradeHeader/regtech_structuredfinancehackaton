package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.CommodityProductDefinition;
import cdm.base.staticdata.asset.common.validation.CommodityProductDefinitionTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.CommodityProductDefinitionValidator;
import cdm.base.staticdata.asset.common.validation.exists.CommodityProductDefinitionOnlyExistsValidator;
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
@RosettaMeta(model=CommodityProductDefinition.class)
public class CommodityProductDefinitionMeta implements RosettaMetaData<CommodityProductDefinition> {

	@Override
	public List<Validator<? super CommodityProductDefinition>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.CommodityProductDefinitionCommodityProductDefinitionChoice.class)
		);
	}
	
	@Override
	public List<Function<? super CommodityProductDefinition, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CommodityProductDefinition> validator() {
		return new CommodityProductDefinitionValidator();
	}

	@Override
	public Validator<? super CommodityProductDefinition> typeFormatValidator() {
		return new CommodityProductDefinitionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CommodityProductDefinition, Set<String>> onlyExistsValidator() {
		return new CommodityProductDefinitionOnlyExistsValidator();
	}
}
