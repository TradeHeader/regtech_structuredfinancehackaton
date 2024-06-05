package cdm.product.common.settlement;

import cdm.product.common.settlement.LoanParticipation;
import cdm.product.common.settlement.LoanParticipation.LoanParticipationBuilder;
import cdm.product.common.settlement.LoanParticipation.LoanParticipationBuilderImpl;
import cdm.product.common.settlement.LoanParticipation.LoanParticipationImpl;
import cdm.product.common.settlement.PCDeliverableObligationCharac;
import cdm.product.common.settlement.PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder;
import cdm.product.common.settlement.PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilderImpl;
import cdm.product.common.settlement.PCDeliverableObligationCharac.PCDeliverableObligationCharacImpl;
import cdm.product.common.settlement.meta.LoanParticipationMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to specify loan with a participation agreement whereby the buyer is capable of creating, or procuring the creation of, a contractual right in favour of the seller that provides the seller with recourse to the participation seller for a specified share in any payments due under the relevant loan which are received by the participation seller. ISDA 2003 Term: Direct Loan Participation.
 * @version ${project.version}
 */
@RosettaDataType(value="LoanParticipation", builder=LoanParticipation.LoanParticipationBuilderImpl.class, version="${project.version}")
public interface LoanParticipation extends PCDeliverableObligationCharac {

	LoanParticipationMeta metaData = new LoanParticipationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * If Direct Loan Participation is specified as a deliverable obligation characteristic, this specifies any requirements for the Qualifying Participation Seller. The requirements may be listed free-form. ISDA 2003 Term: Qualifying Participation Seller.
	 */
	String getQualifyingParticipationSeller();

	/*********************** Build Methods  ***********************/
	LoanParticipation build();
	
	LoanParticipation.LoanParticipationBuilder toBuilder();
	
	static LoanParticipation.LoanParticipationBuilder builder() {
		return new LoanParticipation.LoanParticipationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends LoanParticipation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends LoanParticipation> getType() {
		return LoanParticipation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
		processor.processBasic(path.newSubPath("partialCashSettlement"), Boolean.class, getPartialCashSettlement(), this);
		processor.processBasic(path.newSubPath("qualifyingParticipationSeller"), String.class, getQualifyingParticipationSeller(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface LoanParticipationBuilder extends LoanParticipation, PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder, RosettaModelObjectBuilder {
		LoanParticipation.LoanParticipationBuilder setApplicable(Boolean applicable);
		LoanParticipation.LoanParticipationBuilder setPartialCashSettlement(Boolean partialCashSettlement);
		LoanParticipation.LoanParticipationBuilder setQualifyingParticipationSeller(String qualifyingParticipationSeller);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
			processor.processBasic(path.newSubPath("partialCashSettlement"), Boolean.class, getPartialCashSettlement(), this);
			processor.processBasic(path.newSubPath("qualifyingParticipationSeller"), String.class, getQualifyingParticipationSeller(), this);
		}
		

		LoanParticipation.LoanParticipationBuilder prune();
	}

	/*********************** Immutable Implementation of LoanParticipation  ***********************/
	class LoanParticipationImpl extends PCDeliverableObligationCharac.PCDeliverableObligationCharacImpl implements LoanParticipation {
		private final String qualifyingParticipationSeller;
		
		protected LoanParticipationImpl(LoanParticipation.LoanParticipationBuilder builder) {
			super(builder);
			this.qualifyingParticipationSeller = builder.getQualifyingParticipationSeller();
		}
		
		@Override
		@RosettaAttribute("qualifyingParticipationSeller")
		public String getQualifyingParticipationSeller() {
			return qualifyingParticipationSeller;
		}
		
		@Override
		public LoanParticipation build() {
			return this;
		}
		
		@Override
		public LoanParticipation.LoanParticipationBuilder toBuilder() {
			LoanParticipation.LoanParticipationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(LoanParticipation.LoanParticipationBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getQualifyingParticipationSeller()).ifPresent(builder::setQualifyingParticipationSeller);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			LoanParticipation _that = getType().cast(o);
		
			if (!Objects.equals(qualifyingParticipationSeller, _that.getQualifyingParticipationSeller())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (qualifyingParticipationSeller != null ? qualifyingParticipationSeller.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LoanParticipation {" +
				"qualifyingParticipationSeller=" + this.qualifyingParticipationSeller +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of LoanParticipation  ***********************/
	class LoanParticipationBuilderImpl extends PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilderImpl  implements LoanParticipation.LoanParticipationBuilder {
	
		protected String qualifyingParticipationSeller;
	
		public LoanParticipationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("qualifyingParticipationSeller")
		public String getQualifyingParticipationSeller() {
			return qualifyingParticipationSeller;
		}
		
	
		@Override
		@RosettaAttribute("applicable")
		public LoanParticipation.LoanParticipationBuilder setApplicable(Boolean applicable) {
			this.applicable = applicable==null?null:applicable;
			return this;
		}
		@Override
		@RosettaAttribute("partialCashSettlement")
		public LoanParticipation.LoanParticipationBuilder setPartialCashSettlement(Boolean partialCashSettlement) {
			this.partialCashSettlement = partialCashSettlement==null?null:partialCashSettlement;
			return this;
		}
		@Override
		@RosettaAttribute("qualifyingParticipationSeller")
		public LoanParticipation.LoanParticipationBuilder setQualifyingParticipationSeller(String qualifyingParticipationSeller) {
			this.qualifyingParticipationSeller = qualifyingParticipationSeller==null?null:qualifyingParticipationSeller;
			return this;
		}
		
		@Override
		public LoanParticipation build() {
			return new LoanParticipation.LoanParticipationImpl(this);
		}
		
		@Override
		public LoanParticipation.LoanParticipationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LoanParticipation.LoanParticipationBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getQualifyingParticipationSeller()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LoanParticipation.LoanParticipationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			LoanParticipation.LoanParticipationBuilder o = (LoanParticipation.LoanParticipationBuilder) other;
			
			
			merger.mergeBasic(getQualifyingParticipationSeller(), o.getQualifyingParticipationSeller(), this::setQualifyingParticipationSeller);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			LoanParticipation _that = getType().cast(o);
		
			if (!Objects.equals(qualifyingParticipationSeller, _that.getQualifyingParticipationSeller())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (qualifyingParticipationSeller != null ? qualifyingParticipationSeller.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LoanParticipationBuilder {" +
				"qualifyingParticipationSeller=" + this.qualifyingParticipationSeller +
			'}' + " " + super.toString();
		}
	}
}
