package cdm.product.asset;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.product.asset.DividendPaymentDate;
import cdm.product.asset.DividendPeriod;
import cdm.product.asset.DividendPeriod.DividendPeriodBuilder;
import cdm.product.asset.DividendPeriod.DividendPeriodBuilderImpl;
import cdm.product.asset.DividendPeriod.DividendPeriodImpl;
import cdm.product.asset.meta.DividendPeriodMeta;
import cdm.product.template.Product;
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
 * Time bounded dividend payment periods, each with a dividend payment date per period.
 * @version ${project.version}
 */
@RosettaDataType(value="DividendPeriod", builder=DividendPeriod.DividendPeriodBuilderImpl.class, version="${project.version}")
public interface DividendPeriod extends RosettaModelObject {

	DividendPeriodMeta metaData = new DividendPeriodMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Dividend period start date.
	 */
	DividendPaymentDate getStartDate();
	/**
	 * Dividend period end date.
	 */
	DividendPaymentDate getEndDate();
	/**
	 * Date adjustments for all unadjusted dates in this dividend period.
	 */
	BusinessDayAdjustments getDateAdjustments();
	/**
	 * For basket undeliers, reference to the basket component which is paying dividends in the specified period.
	 */
	Product getBasketConstituent();
	/**
	 * Specifies when the dividend will be paid to the receiver of the equity return. Has the meaning as defined in the ISDA 2002 Equity Derivatives Definitions. Is not applicable in the case of a dividend reinvestment election.
	 */
	DividendPaymentDate getDividendPaymentDate();
	/**
	 * Specifies the dividend valuation dates of the swap.
	 */
	AdjustableOrRelativeDate getDividendValuationDate();

	/*********************** Build Methods  ***********************/
	DividendPeriod build();
	
	DividendPeriod.DividendPeriodBuilder toBuilder();
	
	static DividendPeriod.DividendPeriodBuilder builder() {
		return new DividendPeriod.DividendPeriodBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DividendPeriod> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DividendPeriod> getType() {
		return DividendPeriod.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("startDate"), processor, DividendPaymentDate.class, getStartDate());
		processRosetta(path.newSubPath("endDate"), processor, DividendPaymentDate.class, getEndDate());
		processRosetta(path.newSubPath("dateAdjustments"), processor, BusinessDayAdjustments.class, getDateAdjustments());
		processRosetta(path.newSubPath("basketConstituent"), processor, Product.class, getBasketConstituent());
		processRosetta(path.newSubPath("dividendPaymentDate"), processor, DividendPaymentDate.class, getDividendPaymentDate());
		processRosetta(path.newSubPath("dividendValuationDate"), processor, AdjustableOrRelativeDate.class, getDividendValuationDate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DividendPeriodBuilder extends DividendPeriod, RosettaModelObjectBuilder {
		DividendPaymentDate.DividendPaymentDateBuilder getOrCreateStartDate();
		DividendPaymentDate.DividendPaymentDateBuilder getStartDate();
		DividendPaymentDate.DividendPaymentDateBuilder getOrCreateEndDate();
		DividendPaymentDate.DividendPaymentDateBuilder getEndDate();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreateDateAdjustments();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getDateAdjustments();
		Product.ProductBuilder getOrCreateBasketConstituent();
		Product.ProductBuilder getBasketConstituent();
		DividendPaymentDate.DividendPaymentDateBuilder getOrCreateDividendPaymentDate();
		DividendPaymentDate.DividendPaymentDateBuilder getDividendPaymentDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateDividendValuationDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getDividendValuationDate();
		DividendPeriod.DividendPeriodBuilder setStartDate(DividendPaymentDate startDate);
		DividendPeriod.DividendPeriodBuilder setEndDate(DividendPaymentDate endDate);
		DividendPeriod.DividendPeriodBuilder setDateAdjustments(BusinessDayAdjustments dateAdjustments);
		DividendPeriod.DividendPeriodBuilder setBasketConstituent(Product basketConstituent);
		DividendPeriod.DividendPeriodBuilder setDividendPaymentDate(DividendPaymentDate dividendPaymentDate);
		DividendPeriod.DividendPeriodBuilder setDividendValuationDate(AdjustableOrRelativeDate dividendValuationDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("startDate"), processor, DividendPaymentDate.DividendPaymentDateBuilder.class, getStartDate());
			processRosetta(path.newSubPath("endDate"), processor, DividendPaymentDate.DividendPaymentDateBuilder.class, getEndDate());
			processRosetta(path.newSubPath("dateAdjustments"), processor, BusinessDayAdjustments.BusinessDayAdjustmentsBuilder.class, getDateAdjustments());
			processRosetta(path.newSubPath("basketConstituent"), processor, Product.ProductBuilder.class, getBasketConstituent());
			processRosetta(path.newSubPath("dividendPaymentDate"), processor, DividendPaymentDate.DividendPaymentDateBuilder.class, getDividendPaymentDate());
			processRosetta(path.newSubPath("dividendValuationDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getDividendValuationDate());
		}
		

		DividendPeriod.DividendPeriodBuilder prune();
	}

	/*********************** Immutable Implementation of DividendPeriod  ***********************/
	class DividendPeriodImpl implements DividendPeriod {
		private final DividendPaymentDate startDate;
		private final DividendPaymentDate endDate;
		private final BusinessDayAdjustments dateAdjustments;
		private final Product basketConstituent;
		private final DividendPaymentDate dividendPaymentDate;
		private final AdjustableOrRelativeDate dividendValuationDate;
		
		protected DividendPeriodImpl(DividendPeriod.DividendPeriodBuilder builder) {
			this.startDate = ofNullable(builder.getStartDate()).map(f->f.build()).orElse(null);
			this.endDate = ofNullable(builder.getEndDate()).map(f->f.build()).orElse(null);
			this.dateAdjustments = ofNullable(builder.getDateAdjustments()).map(f->f.build()).orElse(null);
			this.basketConstituent = ofNullable(builder.getBasketConstituent()).map(f->f.build()).orElse(null);
			this.dividendPaymentDate = ofNullable(builder.getDividendPaymentDate()).map(f->f.build()).orElse(null);
			this.dividendValuationDate = ofNullable(builder.getDividendValuationDate()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("startDate")
		public DividendPaymentDate getStartDate() {
			return startDate;
		}
		
		@Override
		@RosettaAttribute("endDate")
		public DividendPaymentDate getEndDate() {
			return endDate;
		}
		
		@Override
		@RosettaAttribute("dateAdjustments")
		public BusinessDayAdjustments getDateAdjustments() {
			return dateAdjustments;
		}
		
		@Override
		@RosettaAttribute("basketConstituent")
		public Product getBasketConstituent() {
			return basketConstituent;
		}
		
		@Override
		@RosettaAttribute("dividendPaymentDate")
		public DividendPaymentDate getDividendPaymentDate() {
			return dividendPaymentDate;
		}
		
		@Override
		@RosettaAttribute("dividendValuationDate")
		public AdjustableOrRelativeDate getDividendValuationDate() {
			return dividendValuationDate;
		}
		
		@Override
		public DividendPeriod build() {
			return this;
		}
		
		@Override
		public DividendPeriod.DividendPeriodBuilder toBuilder() {
			DividendPeriod.DividendPeriodBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DividendPeriod.DividendPeriodBuilder builder) {
			ofNullable(getStartDate()).ifPresent(builder::setStartDate);
			ofNullable(getEndDate()).ifPresent(builder::setEndDate);
			ofNullable(getDateAdjustments()).ifPresent(builder::setDateAdjustments);
			ofNullable(getBasketConstituent()).ifPresent(builder::setBasketConstituent);
			ofNullable(getDividendPaymentDate()).ifPresent(builder::setDividendPaymentDate);
			ofNullable(getDividendValuationDate()).ifPresent(builder::setDividendValuationDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendPeriod _that = getType().cast(o);
		
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			if (!Objects.equals(dateAdjustments, _that.getDateAdjustments())) return false;
			if (!Objects.equals(basketConstituent, _that.getBasketConstituent())) return false;
			if (!Objects.equals(dividendPaymentDate, _that.getDividendPaymentDate())) return false;
			if (!Objects.equals(dividendValuationDate, _that.getDividendValuationDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			_result = 31 * _result + (dateAdjustments != null ? dateAdjustments.hashCode() : 0);
			_result = 31 * _result + (basketConstituent != null ? basketConstituent.hashCode() : 0);
			_result = 31 * _result + (dividendPaymentDate != null ? dividendPaymentDate.hashCode() : 0);
			_result = 31 * _result + (dividendValuationDate != null ? dividendValuationDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendPeriod {" +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate + ", " +
				"dateAdjustments=" + this.dateAdjustments + ", " +
				"basketConstituent=" + this.basketConstituent + ", " +
				"dividendPaymentDate=" + this.dividendPaymentDate + ", " +
				"dividendValuationDate=" + this.dividendValuationDate +
			'}';
		}
	}

	/*********************** Builder Implementation of DividendPeriod  ***********************/
	class DividendPeriodBuilderImpl implements DividendPeriod.DividendPeriodBuilder {
	
		protected DividendPaymentDate.DividendPaymentDateBuilder startDate;
		protected DividendPaymentDate.DividendPaymentDateBuilder endDate;
		protected BusinessDayAdjustments.BusinessDayAdjustmentsBuilder dateAdjustments;
		protected Product.ProductBuilder basketConstituent;
		protected DividendPaymentDate.DividendPaymentDateBuilder dividendPaymentDate;
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder dividendValuationDate;
	
		public DividendPeriodBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("startDate")
		public DividendPaymentDate.DividendPaymentDateBuilder getStartDate() {
			return startDate;
		}
		
		@Override
		public DividendPaymentDate.DividendPaymentDateBuilder getOrCreateStartDate() {
			DividendPaymentDate.DividendPaymentDateBuilder result;
			if (startDate!=null) {
				result = startDate;
			}
			else {
				result = startDate = DividendPaymentDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("endDate")
		public DividendPaymentDate.DividendPaymentDateBuilder getEndDate() {
			return endDate;
		}
		
		@Override
		public DividendPaymentDate.DividendPaymentDateBuilder getOrCreateEndDate() {
			DividendPaymentDate.DividendPaymentDateBuilder result;
			if (endDate!=null) {
				result = endDate;
			}
			else {
				result = endDate = DividendPaymentDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dateAdjustments")
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getDateAdjustments() {
			return dateAdjustments;
		}
		
		@Override
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreateDateAdjustments() {
			BusinessDayAdjustments.BusinessDayAdjustmentsBuilder result;
			if (dateAdjustments!=null) {
				result = dateAdjustments;
			}
			else {
				result = dateAdjustments = BusinessDayAdjustments.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("basketConstituent")
		public Product.ProductBuilder getBasketConstituent() {
			return basketConstituent;
		}
		
		@Override
		public Product.ProductBuilder getOrCreateBasketConstituent() {
			Product.ProductBuilder result;
			if (basketConstituent!=null) {
				result = basketConstituent;
			}
			else {
				result = basketConstituent = Product.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dividendPaymentDate")
		public DividendPaymentDate.DividendPaymentDateBuilder getDividendPaymentDate() {
			return dividendPaymentDate;
		}
		
		@Override
		public DividendPaymentDate.DividendPaymentDateBuilder getOrCreateDividendPaymentDate() {
			DividendPaymentDate.DividendPaymentDateBuilder result;
			if (dividendPaymentDate!=null) {
				result = dividendPaymentDate;
			}
			else {
				result = dividendPaymentDate = DividendPaymentDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dividendValuationDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getDividendValuationDate() {
			return dividendValuationDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateDividendValuationDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (dividendValuationDate!=null) {
				result = dividendValuationDate;
			}
			else {
				result = dividendValuationDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("startDate")
		public DividendPeriod.DividendPeriodBuilder setStartDate(DividendPaymentDate startDate) {
			this.startDate = startDate==null?null:startDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("endDate")
		public DividendPeriod.DividendPeriodBuilder setEndDate(DividendPaymentDate endDate) {
			this.endDate = endDate==null?null:endDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dateAdjustments")
		public DividendPeriod.DividendPeriodBuilder setDateAdjustments(BusinessDayAdjustments dateAdjustments) {
			this.dateAdjustments = dateAdjustments==null?null:dateAdjustments.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("basketConstituent")
		public DividendPeriod.DividendPeriodBuilder setBasketConstituent(Product basketConstituent) {
			this.basketConstituent = basketConstituent==null?null:basketConstituent.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dividendPaymentDate")
		public DividendPeriod.DividendPeriodBuilder setDividendPaymentDate(DividendPaymentDate dividendPaymentDate) {
			this.dividendPaymentDate = dividendPaymentDate==null?null:dividendPaymentDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dividendValuationDate")
		public DividendPeriod.DividendPeriodBuilder setDividendValuationDate(AdjustableOrRelativeDate dividendValuationDate) {
			this.dividendValuationDate = dividendValuationDate==null?null:dividendValuationDate.toBuilder();
			return this;
		}
		
		@Override
		public DividendPeriod build() {
			return new DividendPeriod.DividendPeriodImpl(this);
		}
		
		@Override
		public DividendPeriod.DividendPeriodBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendPeriod.DividendPeriodBuilder prune() {
			if (startDate!=null && !startDate.prune().hasData()) startDate = null;
			if (endDate!=null && !endDate.prune().hasData()) endDate = null;
			if (dateAdjustments!=null && !dateAdjustments.prune().hasData()) dateAdjustments = null;
			if (basketConstituent!=null && !basketConstituent.prune().hasData()) basketConstituent = null;
			if (dividendPaymentDate!=null && !dividendPaymentDate.prune().hasData()) dividendPaymentDate = null;
			if (dividendValuationDate!=null && !dividendValuationDate.prune().hasData()) dividendValuationDate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStartDate()!=null && getStartDate().hasData()) return true;
			if (getEndDate()!=null && getEndDate().hasData()) return true;
			if (getDateAdjustments()!=null && getDateAdjustments().hasData()) return true;
			if (getBasketConstituent()!=null && getBasketConstituent().hasData()) return true;
			if (getDividendPaymentDate()!=null && getDividendPaymentDate().hasData()) return true;
			if (getDividendValuationDate()!=null && getDividendValuationDate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendPeriod.DividendPeriodBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DividendPeriod.DividendPeriodBuilder o = (DividendPeriod.DividendPeriodBuilder) other;
			
			merger.mergeRosetta(getStartDate(), o.getStartDate(), this::setStartDate);
			merger.mergeRosetta(getEndDate(), o.getEndDate(), this::setEndDate);
			merger.mergeRosetta(getDateAdjustments(), o.getDateAdjustments(), this::setDateAdjustments);
			merger.mergeRosetta(getBasketConstituent(), o.getBasketConstituent(), this::setBasketConstituent);
			merger.mergeRosetta(getDividendPaymentDate(), o.getDividendPaymentDate(), this::setDividendPaymentDate);
			merger.mergeRosetta(getDividendValuationDate(), o.getDividendValuationDate(), this::setDividendValuationDate);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendPeriod _that = getType().cast(o);
		
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			if (!Objects.equals(dateAdjustments, _that.getDateAdjustments())) return false;
			if (!Objects.equals(basketConstituent, _that.getBasketConstituent())) return false;
			if (!Objects.equals(dividendPaymentDate, _that.getDividendPaymentDate())) return false;
			if (!Objects.equals(dividendValuationDate, _that.getDividendValuationDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			_result = 31 * _result + (dateAdjustments != null ? dateAdjustments.hashCode() : 0);
			_result = 31 * _result + (basketConstituent != null ? basketConstituent.hashCode() : 0);
			_result = 31 * _result + (dividendPaymentDate != null ? dividendPaymentDate.hashCode() : 0);
			_result = 31 * _result + (dividendValuationDate != null ? dividendValuationDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendPeriodBuilder {" +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate + ", " +
				"dateAdjustments=" + this.dateAdjustments + ", " +
				"basketConstituent=" + this.basketConstituent + ", " +
				"dividendPaymentDate=" + this.dividendPaymentDate + ", " +
				"dividendValuationDate=" + this.dividendValuationDate +
			'}';
		}
	}
}
