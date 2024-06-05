package cdm.event.position.meta;

import cdm.event.position.AvailableInventory;
import cdm.event.position.validation.AvailableInventoryTypeFormatValidator;
import cdm.event.position.validation.AvailableInventoryValidator;
import cdm.event.position.validation.exists.AvailableInventoryOnlyExistsValidator;
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
@RosettaMeta(model=AvailableInventory.class)
public class AvailableInventoryMeta implements RosettaMetaData<AvailableInventory> {

	@Override
	public List<Validator<? super AvailableInventory>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.position.validation.datarule.AvailableInventoryValidPartyRole.class)
		);
	}
	
	@Override
	public List<Function<? super AvailableInventory, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AvailableInventory> validator() {
		return new AvailableInventoryValidator();
	}

	@Override
	public Validator<? super AvailableInventory> typeFormatValidator() {
		return new AvailableInventoryTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AvailableInventory, Set<String>> onlyExistsValidator() {
		return new AvailableInventoryOnlyExistsValidator();
	}
}
