package cdm.product.template;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.base.datetime.RelativeDateOffset;
import cdm.base.staticdata.party.PartyRole;
import cdm.observable.asset.Price;
import cdm.product.template.EvergreenProvision;
import cdm.product.template.EvergreenProvision.EvergreenProvisionBuilder;
import cdm.product.template.EvergreenProvision.EvergreenProvisionBuilderImpl;
import cdm.product.template.EvergreenProvision.EvergreenProvisionImpl;
import cdm.product.template.meta.EvergreenProvisionMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.time.ZonedDateTime;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specifies a transaction which automatically extends for a specified timeframe until the exercise of an embedded option.
 * @version ${project.version}
 */
@RosettaDataType(value="EvergreenProvision", builder=EvergreenProvision.EvergreenProvisionBuilderImpl.class, version="${project.version}")
public interface EvergreenProvision extends RosettaModelObject {

	EvergreenProvisionMeta metaData = new EvergreenProvisionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * If evergreen termination is not available to both parties then this component specifies the buyer and seller of the option.
	 */
	PartyRole getSinglePartyOption();
	/**
	 * The length of each evergreen extension period relative to the effective date of the preceding contract.
	 */
	RelativeDateOffset getNoticePeriod();
	/**
	 * Defines the minimum period before an evergreen is scheduled to terminate that notice can be given that it will terminate beyond the scheduled termination date.
	 */
	RelativeDateOffset getNoticeDeadlinePeriod();
	/**
	 * A specific date and time for the notice deadline
	 */
	ZonedDateTime getNoticeDeadlineDateTime();
	/**
	 * The frequency with which the evergreen contract will be extended if notice is not given.
	 */
	AdjustableRelativeOrPeriodicDates getExtensionFrequency();
	/**
	 * An optional adjustment to the rate for the last period of the evergreen i.e. the period from when notice is given to stop rolling the contract through to the termination date.
	 */
	Price getFinalPeriodFeeAdjustment();

	/*********************** Build Methods  ***********************/
	EvergreenProvision build();
	
	EvergreenProvision.EvergreenProvisionBuilder toBuilder();
	
	static EvergreenProvision.EvergreenProvisionBuilder builder() {
		return new EvergreenProvision.EvergreenProvisionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EvergreenProvision> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EvergreenProvision> getType() {
		return EvergreenProvision.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("singlePartyOption"), processor, PartyRole.class, getSinglePartyOption());
		processRosetta(path.newSubPath("noticePeriod"), processor, RelativeDateOffset.class, getNoticePeriod());
		processRosetta(path.newSubPath("noticeDeadlinePeriod"), processor, RelativeDateOffset.class, getNoticeDeadlinePeriod());
		processor.processBasic(path.newSubPath("noticeDeadlineDateTime"), ZonedDateTime.class, getNoticeDeadlineDateTime(), this);
		processRosetta(path.newSubPath("extensionFrequency"), processor, AdjustableRelativeOrPeriodicDates.class, getExtensionFrequency());
		processRosetta(path.newSubPath("finalPeriodFeeAdjustment"), processor, Price.class, getFinalPeriodFeeAdjustment());
	}
	

	/*********************** Builder Interface  ***********************/
	interface EvergreenProvisionBuilder extends EvergreenProvision, RosettaModelObjectBuilder {
		PartyRole.PartyRoleBuilder getOrCreateSinglePartyOption();
		PartyRole.PartyRoleBuilder getSinglePartyOption();
		RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateNoticePeriod();
		RelativeDateOffset.RelativeDateOffsetBuilder getNoticePeriod();
		RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateNoticeDeadlinePeriod();
		RelativeDateOffset.RelativeDateOffsetBuilder getNoticeDeadlinePeriod();
		AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getOrCreateExtensionFrequency();
		AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getExtensionFrequency();
		Price.PriceBuilder getOrCreateFinalPeriodFeeAdjustment();
		Price.PriceBuilder getFinalPeriodFeeAdjustment();
		EvergreenProvision.EvergreenProvisionBuilder setSinglePartyOption(PartyRole singlePartyOption);
		EvergreenProvision.EvergreenProvisionBuilder setNoticePeriod(RelativeDateOffset noticePeriod);
		EvergreenProvision.EvergreenProvisionBuilder setNoticeDeadlinePeriod(RelativeDateOffset noticeDeadlinePeriod);
		EvergreenProvision.EvergreenProvisionBuilder setNoticeDeadlineDateTime(ZonedDateTime noticeDeadlineDateTime);
		EvergreenProvision.EvergreenProvisionBuilder setExtensionFrequency(AdjustableRelativeOrPeriodicDates extensionFrequency);
		EvergreenProvision.EvergreenProvisionBuilder setFinalPeriodFeeAdjustment(Price finalPeriodFeeAdjustment);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("singlePartyOption"), processor, PartyRole.PartyRoleBuilder.class, getSinglePartyOption());
			processRosetta(path.newSubPath("noticePeriod"), processor, RelativeDateOffset.RelativeDateOffsetBuilder.class, getNoticePeriod());
			processRosetta(path.newSubPath("noticeDeadlinePeriod"), processor, RelativeDateOffset.RelativeDateOffsetBuilder.class, getNoticeDeadlinePeriod());
			processor.processBasic(path.newSubPath("noticeDeadlineDateTime"), ZonedDateTime.class, getNoticeDeadlineDateTime(), this);
			processRosetta(path.newSubPath("extensionFrequency"), processor, AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder.class, getExtensionFrequency());
			processRosetta(path.newSubPath("finalPeriodFeeAdjustment"), processor, Price.PriceBuilder.class, getFinalPeriodFeeAdjustment());
		}
		

		EvergreenProvision.EvergreenProvisionBuilder prune();
	}

	/*********************** Immutable Implementation of EvergreenProvision  ***********************/
	class EvergreenProvisionImpl implements EvergreenProvision {
		private final PartyRole singlePartyOption;
		private final RelativeDateOffset noticePeriod;
		private final RelativeDateOffset noticeDeadlinePeriod;
		private final ZonedDateTime noticeDeadlineDateTime;
		private final AdjustableRelativeOrPeriodicDates extensionFrequency;
		private final Price finalPeriodFeeAdjustment;
		
		protected EvergreenProvisionImpl(EvergreenProvision.EvergreenProvisionBuilder builder) {
			this.singlePartyOption = ofNullable(builder.getSinglePartyOption()).map(f->f.build()).orElse(null);
			this.noticePeriod = ofNullable(builder.getNoticePeriod()).map(f->f.build()).orElse(null);
			this.noticeDeadlinePeriod = ofNullable(builder.getNoticeDeadlinePeriod()).map(f->f.build()).orElse(null);
			this.noticeDeadlineDateTime = builder.getNoticeDeadlineDateTime();
			this.extensionFrequency = ofNullable(builder.getExtensionFrequency()).map(f->f.build()).orElse(null);
			this.finalPeriodFeeAdjustment = ofNullable(builder.getFinalPeriodFeeAdjustment()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("singlePartyOption")
		public PartyRole getSinglePartyOption() {
			return singlePartyOption;
		}
		
		@Override
		@RosettaAttribute("noticePeriod")
		public RelativeDateOffset getNoticePeriod() {
			return noticePeriod;
		}
		
		@Override
		@RosettaAttribute("noticeDeadlinePeriod")
		public RelativeDateOffset getNoticeDeadlinePeriod() {
			return noticeDeadlinePeriod;
		}
		
		@Override
		@RosettaAttribute("noticeDeadlineDateTime")
		public ZonedDateTime getNoticeDeadlineDateTime() {
			return noticeDeadlineDateTime;
		}
		
		@Override
		@RosettaAttribute("extensionFrequency")
		public AdjustableRelativeOrPeriodicDates getExtensionFrequency() {
			return extensionFrequency;
		}
		
		@Override
		@RosettaAttribute("finalPeriodFeeAdjustment")
		public Price getFinalPeriodFeeAdjustment() {
			return finalPeriodFeeAdjustment;
		}
		
		@Override
		public EvergreenProvision build() {
			return this;
		}
		
		@Override
		public EvergreenProvision.EvergreenProvisionBuilder toBuilder() {
			EvergreenProvision.EvergreenProvisionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EvergreenProvision.EvergreenProvisionBuilder builder) {
			ofNullable(getSinglePartyOption()).ifPresent(builder::setSinglePartyOption);
			ofNullable(getNoticePeriod()).ifPresent(builder::setNoticePeriod);
			ofNullable(getNoticeDeadlinePeriod()).ifPresent(builder::setNoticeDeadlinePeriod);
			ofNullable(getNoticeDeadlineDateTime()).ifPresent(builder::setNoticeDeadlineDateTime);
			ofNullable(getExtensionFrequency()).ifPresent(builder::setExtensionFrequency);
			ofNullable(getFinalPeriodFeeAdjustment()).ifPresent(builder::setFinalPeriodFeeAdjustment);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EvergreenProvision _that = getType().cast(o);
		
			if (!Objects.equals(singlePartyOption, _that.getSinglePartyOption())) return false;
			if (!Objects.equals(noticePeriod, _that.getNoticePeriod())) return false;
			if (!Objects.equals(noticeDeadlinePeriod, _that.getNoticeDeadlinePeriod())) return false;
			if (!Objects.equals(noticeDeadlineDateTime, _that.getNoticeDeadlineDateTime())) return false;
			if (!Objects.equals(extensionFrequency, _that.getExtensionFrequency())) return false;
			if (!Objects.equals(finalPeriodFeeAdjustment, _that.getFinalPeriodFeeAdjustment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (singlePartyOption != null ? singlePartyOption.hashCode() : 0);
			_result = 31 * _result + (noticePeriod != null ? noticePeriod.hashCode() : 0);
			_result = 31 * _result + (noticeDeadlinePeriod != null ? noticeDeadlinePeriod.hashCode() : 0);
			_result = 31 * _result + (noticeDeadlineDateTime != null ? noticeDeadlineDateTime.hashCode() : 0);
			_result = 31 * _result + (extensionFrequency != null ? extensionFrequency.hashCode() : 0);
			_result = 31 * _result + (finalPeriodFeeAdjustment != null ? finalPeriodFeeAdjustment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EvergreenProvision {" +
				"singlePartyOption=" + this.singlePartyOption + ", " +
				"noticePeriod=" + this.noticePeriod + ", " +
				"noticeDeadlinePeriod=" + this.noticeDeadlinePeriod + ", " +
				"noticeDeadlineDateTime=" + this.noticeDeadlineDateTime + ", " +
				"extensionFrequency=" + this.extensionFrequency + ", " +
				"finalPeriodFeeAdjustment=" + this.finalPeriodFeeAdjustment +
			'}';
		}
	}

	/*********************** Builder Implementation of EvergreenProvision  ***********************/
	class EvergreenProvisionBuilderImpl implements EvergreenProvision.EvergreenProvisionBuilder {
	
		protected PartyRole.PartyRoleBuilder singlePartyOption;
		protected RelativeDateOffset.RelativeDateOffsetBuilder noticePeriod;
		protected RelativeDateOffset.RelativeDateOffsetBuilder noticeDeadlinePeriod;
		protected ZonedDateTime noticeDeadlineDateTime;
		protected AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder extensionFrequency;
		protected Price.PriceBuilder finalPeriodFeeAdjustment;
	
		public EvergreenProvisionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("singlePartyOption")
		public PartyRole.PartyRoleBuilder getSinglePartyOption() {
			return singlePartyOption;
		}
		
		@Override
		public PartyRole.PartyRoleBuilder getOrCreateSinglePartyOption() {
			PartyRole.PartyRoleBuilder result;
			if (singlePartyOption!=null) {
				result = singlePartyOption;
			}
			else {
				result = singlePartyOption = PartyRole.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("noticePeriod")
		public RelativeDateOffset.RelativeDateOffsetBuilder getNoticePeriod() {
			return noticePeriod;
		}
		
		@Override
		public RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateNoticePeriod() {
			RelativeDateOffset.RelativeDateOffsetBuilder result;
			if (noticePeriod!=null) {
				result = noticePeriod;
			}
			else {
				result = noticePeriod = RelativeDateOffset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("noticeDeadlinePeriod")
		public RelativeDateOffset.RelativeDateOffsetBuilder getNoticeDeadlinePeriod() {
			return noticeDeadlinePeriod;
		}
		
		@Override
		public RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateNoticeDeadlinePeriod() {
			RelativeDateOffset.RelativeDateOffsetBuilder result;
			if (noticeDeadlinePeriod!=null) {
				result = noticeDeadlinePeriod;
			}
			else {
				result = noticeDeadlinePeriod = RelativeDateOffset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("noticeDeadlineDateTime")
		public ZonedDateTime getNoticeDeadlineDateTime() {
			return noticeDeadlineDateTime;
		}
		
		@Override
		@RosettaAttribute("extensionFrequency")
		public AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getExtensionFrequency() {
			return extensionFrequency;
		}
		
		@Override
		public AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getOrCreateExtensionFrequency() {
			AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder result;
			if (extensionFrequency!=null) {
				result = extensionFrequency;
			}
			else {
				result = extensionFrequency = AdjustableRelativeOrPeriodicDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("finalPeriodFeeAdjustment")
		public Price.PriceBuilder getFinalPeriodFeeAdjustment() {
			return finalPeriodFeeAdjustment;
		}
		
		@Override
		public Price.PriceBuilder getOrCreateFinalPeriodFeeAdjustment() {
			Price.PriceBuilder result;
			if (finalPeriodFeeAdjustment!=null) {
				result = finalPeriodFeeAdjustment;
			}
			else {
				result = finalPeriodFeeAdjustment = Price.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("singlePartyOption")
		public EvergreenProvision.EvergreenProvisionBuilder setSinglePartyOption(PartyRole singlePartyOption) {
			this.singlePartyOption = singlePartyOption==null?null:singlePartyOption.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("noticePeriod")
		public EvergreenProvision.EvergreenProvisionBuilder setNoticePeriod(RelativeDateOffset noticePeriod) {
			this.noticePeriod = noticePeriod==null?null:noticePeriod.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("noticeDeadlinePeriod")
		public EvergreenProvision.EvergreenProvisionBuilder setNoticeDeadlinePeriod(RelativeDateOffset noticeDeadlinePeriod) {
			this.noticeDeadlinePeriod = noticeDeadlinePeriod==null?null:noticeDeadlinePeriod.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("noticeDeadlineDateTime")
		public EvergreenProvision.EvergreenProvisionBuilder setNoticeDeadlineDateTime(ZonedDateTime noticeDeadlineDateTime) {
			this.noticeDeadlineDateTime = noticeDeadlineDateTime==null?null:noticeDeadlineDateTime;
			return this;
		}
		@Override
		@RosettaAttribute("extensionFrequency")
		public EvergreenProvision.EvergreenProvisionBuilder setExtensionFrequency(AdjustableRelativeOrPeriodicDates extensionFrequency) {
			this.extensionFrequency = extensionFrequency==null?null:extensionFrequency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("finalPeriodFeeAdjustment")
		public EvergreenProvision.EvergreenProvisionBuilder setFinalPeriodFeeAdjustment(Price finalPeriodFeeAdjustment) {
			this.finalPeriodFeeAdjustment = finalPeriodFeeAdjustment==null?null:finalPeriodFeeAdjustment.toBuilder();
			return this;
		}
		
		@Override
		public EvergreenProvision build() {
			return new EvergreenProvision.EvergreenProvisionImpl(this);
		}
		
		@Override
		public EvergreenProvision.EvergreenProvisionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EvergreenProvision.EvergreenProvisionBuilder prune() {
			if (singlePartyOption!=null && !singlePartyOption.prune().hasData()) singlePartyOption = null;
			if (noticePeriod!=null && !noticePeriod.prune().hasData()) noticePeriod = null;
			if (noticeDeadlinePeriod!=null && !noticeDeadlinePeriod.prune().hasData()) noticeDeadlinePeriod = null;
			if (extensionFrequency!=null && !extensionFrequency.prune().hasData()) extensionFrequency = null;
			if (finalPeriodFeeAdjustment!=null && !finalPeriodFeeAdjustment.prune().hasData()) finalPeriodFeeAdjustment = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSinglePartyOption()!=null && getSinglePartyOption().hasData()) return true;
			if (getNoticePeriod()!=null && getNoticePeriod().hasData()) return true;
			if (getNoticeDeadlinePeriod()!=null && getNoticeDeadlinePeriod().hasData()) return true;
			if (getNoticeDeadlineDateTime()!=null) return true;
			if (getExtensionFrequency()!=null && getExtensionFrequency().hasData()) return true;
			if (getFinalPeriodFeeAdjustment()!=null && getFinalPeriodFeeAdjustment().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EvergreenProvision.EvergreenProvisionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			EvergreenProvision.EvergreenProvisionBuilder o = (EvergreenProvision.EvergreenProvisionBuilder) other;
			
			merger.mergeRosetta(getSinglePartyOption(), o.getSinglePartyOption(), this::setSinglePartyOption);
			merger.mergeRosetta(getNoticePeriod(), o.getNoticePeriod(), this::setNoticePeriod);
			merger.mergeRosetta(getNoticeDeadlinePeriod(), o.getNoticeDeadlinePeriod(), this::setNoticeDeadlinePeriod);
			merger.mergeRosetta(getExtensionFrequency(), o.getExtensionFrequency(), this::setExtensionFrequency);
			merger.mergeRosetta(getFinalPeriodFeeAdjustment(), o.getFinalPeriodFeeAdjustment(), this::setFinalPeriodFeeAdjustment);
			
			merger.mergeBasic(getNoticeDeadlineDateTime(), o.getNoticeDeadlineDateTime(), this::setNoticeDeadlineDateTime);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EvergreenProvision _that = getType().cast(o);
		
			if (!Objects.equals(singlePartyOption, _that.getSinglePartyOption())) return false;
			if (!Objects.equals(noticePeriod, _that.getNoticePeriod())) return false;
			if (!Objects.equals(noticeDeadlinePeriod, _that.getNoticeDeadlinePeriod())) return false;
			if (!Objects.equals(noticeDeadlineDateTime, _that.getNoticeDeadlineDateTime())) return false;
			if (!Objects.equals(extensionFrequency, _that.getExtensionFrequency())) return false;
			if (!Objects.equals(finalPeriodFeeAdjustment, _that.getFinalPeriodFeeAdjustment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (singlePartyOption != null ? singlePartyOption.hashCode() : 0);
			_result = 31 * _result + (noticePeriod != null ? noticePeriod.hashCode() : 0);
			_result = 31 * _result + (noticeDeadlinePeriod != null ? noticeDeadlinePeriod.hashCode() : 0);
			_result = 31 * _result + (noticeDeadlineDateTime != null ? noticeDeadlineDateTime.hashCode() : 0);
			_result = 31 * _result + (extensionFrequency != null ? extensionFrequency.hashCode() : 0);
			_result = 31 * _result + (finalPeriodFeeAdjustment != null ? finalPeriodFeeAdjustment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EvergreenProvisionBuilder {" +
				"singlePartyOption=" + this.singlePartyOption + ", " +
				"noticePeriod=" + this.noticePeriod + ", " +
				"noticeDeadlinePeriod=" + this.noticeDeadlinePeriod + ", " +
				"noticeDeadlineDateTime=" + this.noticeDeadlineDateTime + ", " +
				"extensionFrequency=" + this.extensionFrequency + ", " +
				"finalPeriodFeeAdjustment=" + this.finalPeriodFeeAdjustment +
			'}';
		}
	}
}
