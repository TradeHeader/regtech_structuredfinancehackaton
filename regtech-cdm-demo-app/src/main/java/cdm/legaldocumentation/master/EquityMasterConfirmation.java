package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.EquityMasterConfirmation;
import cdm.legaldocumentation.master.EquityMasterConfirmation.EquityMasterConfirmationBuilder;
import cdm.legaldocumentation.master.EquityMasterConfirmation.EquityMasterConfirmationBuilderImpl;
import cdm.legaldocumentation.master.EquityMasterConfirmation.EquityMasterConfirmationImpl;
import cdm.legaldocumentation.master.MasterConfirmationBase;
import cdm.legaldocumentation.master.MasterConfirmationBase.MasterConfirmationBaseBuilder;
import cdm.legaldocumentation.master.MasterConfirmationBase.MasterConfirmationBaseBuilderImpl;
import cdm.legaldocumentation.master.MasterConfirmationBase.MasterConfirmationBaseImpl;
import cdm.legaldocumentation.master.meta.EquityMasterConfirmationMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * Specification for General Terms and Elections of an Equity Master Confirmation that is applicable across multiple Equity confirmations and is referenced by each of these confirmations, an example of which being the 2018 ISDA CDM Equity Confirmation for Security Equity Swap.
 * @version ${project.version}
 */
@RosettaDataType(value="EquityMasterConfirmation", builder=EquityMasterConfirmation.EquityMasterConfirmationBuilderImpl.class, version="${project.version}")
public interface EquityMasterConfirmation extends MasterConfirmationBase {

	EquityMasterConfirmationMeta metaData = new EquityMasterConfirmationMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	EquityMasterConfirmation build();
	
	EquityMasterConfirmation.EquityMasterConfirmationBuilder toBuilder();
	
	static EquityMasterConfirmation.EquityMasterConfirmationBuilder builder() {
		return new EquityMasterConfirmation.EquityMasterConfirmationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EquityMasterConfirmation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EquityMasterConfirmation> getType() {
		return EquityMasterConfirmation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
	}
	

	/*********************** Builder Interface  ***********************/
	interface EquityMasterConfirmationBuilder extends EquityMasterConfirmation, MasterConfirmationBase.MasterConfirmationBaseBuilder, RosettaModelObjectBuilder {

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
		}
		

		EquityMasterConfirmation.EquityMasterConfirmationBuilder prune();
	}

	/*********************** Immutable Implementation of EquityMasterConfirmation  ***********************/
	class EquityMasterConfirmationImpl extends MasterConfirmationBase.MasterConfirmationBaseImpl implements EquityMasterConfirmation {
		
		protected EquityMasterConfirmationImpl(EquityMasterConfirmation.EquityMasterConfirmationBuilder builder) {
			super(builder);
		}
		
		@Override
		public EquityMasterConfirmation build() {
			return this;
		}
		
		@Override
		public EquityMasterConfirmation.EquityMasterConfirmationBuilder toBuilder() {
			EquityMasterConfirmation.EquityMasterConfirmationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EquityMasterConfirmation.EquityMasterConfirmationBuilder builder) {
			super.setBuilderFields(builder);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			return _result;
		}
		
		@Override
		public String toString() {
			return "EquityMasterConfirmation {" +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of EquityMasterConfirmation  ***********************/
	class EquityMasterConfirmationBuilderImpl extends MasterConfirmationBase.MasterConfirmationBaseBuilderImpl  implements EquityMasterConfirmation.EquityMasterConfirmationBuilder {
	
	
		public EquityMasterConfirmationBuilderImpl() {
		}
	
	
		
		@Override
		public EquityMasterConfirmation build() {
			return new EquityMasterConfirmation.EquityMasterConfirmationImpl(this);
		}
		
		@Override
		public EquityMasterConfirmation.EquityMasterConfirmationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EquityMasterConfirmation.EquityMasterConfirmationBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EquityMasterConfirmation.EquityMasterConfirmationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			EquityMasterConfirmation.EquityMasterConfirmationBuilder o = (EquityMasterConfirmation.EquityMasterConfirmationBuilder) other;
			
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			return _result;
		}
		
		@Override
		public String toString() {
			return "EquityMasterConfirmationBuilder {" +
			'}' + " " + super.toString();
		}
	}
}
