package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.AssetPool;
import cdm.base.staticdata.asset.common.validation.AssetPoolTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.AssetPoolValidator;
import cdm.base.staticdata.asset.common.validation.exists.AssetPoolOnlyExistsValidator;
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
@RosettaMeta(model=AssetPool.class)
public class AssetPoolMeta implements RosettaMetaData<AssetPool> {

	@Override
	public List<Validator<? super AssetPool>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.AssetPoolEffectiveDate.class)
		);
	}
	
	@Override
	public List<Function<? super AssetPool, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AssetPool> validator() {
		return new AssetPoolValidator();
	}

	@Override
	public Validator<? super AssetPool> typeFormatValidator() {
		return new AssetPoolTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AssetPool, Set<String>> onlyExistsValidator() {
		return new AssetPoolOnlyExistsValidator();
	}
}
