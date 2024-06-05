package cdm.product.asset.meta;

import cdm.product.asset.ReferencePoolItem;
import cdm.product.asset.validation.ReferencePoolItemTypeFormatValidator;
import cdm.product.asset.validation.ReferencePoolItemValidator;
import cdm.product.asset.validation.exists.ReferencePoolItemOnlyExistsValidator;
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
@RosettaMeta(model=ReferencePoolItem.class)
public class ReferencePoolItemMeta implements RosettaMetaData<ReferencePoolItem> {

	@Override
	public List<Validator<? super ReferencePoolItem>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.ReferencePoolItemSettlementChoice.class)
		);
	}
	
	@Override
	public List<Function<? super ReferencePoolItem, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ReferencePoolItem> validator() {
		return new ReferencePoolItemValidator();
	}

	@Override
	public Validator<? super ReferencePoolItem> typeFormatValidator() {
		return new ReferencePoolItemTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ReferencePoolItem, Set<String>> onlyExistsValidator() {
		return new ReferencePoolItemOnlyExistsValidator();
	}
}
